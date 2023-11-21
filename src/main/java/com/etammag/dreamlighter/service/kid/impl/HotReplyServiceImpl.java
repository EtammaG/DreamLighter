package com.etammag.dreamlighter.service.kid.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.etammag.icommon.exception.CustomException;
import com.etammag.dreamlighter.entity.common.Comment;
import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.HotReplyDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.entity.kid.db.ReplyHotComment;
import com.etammag.dreamlighter.entity.kid.db.ReplyHotLike;
import com.etammag.dreamlighter.mapper.kid.HotReplyMapper;
import com.etammag.dreamlighter.mapper.kid.mp.KidMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyHotCommentMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyHotLikeMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.kid.HotReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotReplyServiceImpl implements HotReplyService {

    private final String redisPrefix;
    private final String allKey;
    private final String sizeKey;
    private final String likeKeyPrefix;
    private final String commentKeyPrefix;

    private final HotReplyMapper hotReplyMapper;

    private final ReplyHotCommentMapper replyHotCommentMapper;
    private final ReplyHotLikeMapper replyHotLikeMapper;

    private final KidMapper kidMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public HotReplyServiceImpl(
            @Value("${dream-lighter.redis.hot.reply}") String redisPrefix,
            HotReplyMapper hotReplyMapper, ReplyHotCommentMapper replyHotCommentMapper, ReplyHotLikeMapper replyHotLikeMapper, KidMapper kidMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.hotReplyMapper = hotReplyMapper;
        this.replyHotCommentMapper = replyHotCommentMapper;
        this.replyHotLikeMapper = replyHotLikeMapper;
        this.kidMapper = kidMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.allKey = redisPrefix + "all";
        this.sizeKey = redisPrefix + "size";
        this.likeKeyPrefix = redisPrefix + "like:";
        this.commentKeyPrefix = redisPrefix + "comment:";
    }

    @Override
    public List<HotReplyDto> getAll() {
        List<HotReplyDto> hots = JSON.parseArray(stringRedisTemplate.opsForValue().get(allKey), HotReplyDto.class);
        if (hots == null || hots.isEmpty()) return new LinkedList<>();
        String kidId = BaseInfoContext.get().getId().toString();
        for (HotReplyDto hot : hots) {
            hot.setLiked(stringRedisTemplate.opsForSet().isMember(likeKeyPrefix + hot.getHotId(), kidId));
            Long size = stringRedisTemplate.opsForSet().size(likeKeyPrefix + hot.getHotId());
            hot.setLikeNum(size == null ? 0 : size.intValue());
        }
        return hots;
    }

    @Override
    public void like(long hotId) {
        Long num = stringRedisTemplate.opsForSet().add(likeKeyPrefix + hotId, BaseInfoContext.get().getId().toString());
        if (num == null || num == 0) throw new CustomException("不能重复点赞");
    }

    @Override
    public void unlike(long hotId) {
        Long num = stringRedisTemplate.opsForSet().remove(likeKeyPrefix + hotId, BaseInfoContext.get().getId().toString());
        if (num == null || num == 0) throw new CustomException("并未点赞");
    }

    @Override
    public List<CommentDto> getComment(String hotId) {
        String key = commentKeyPrefix + hotId;
        Long size = stringRedisTemplate.opsForList().size(key);
        if (size == null || size == 0) return new LinkedList<>();
        List<String> jsons = stringRedisTemplate.opsForList().range(key, 0, size);
        if (jsons == null || jsons.isEmpty()) return new LinkedList<>();
        return jsons.stream().map((json) -> JSON.parseObject(json, CommentDto.class)).collect(Collectors.toList());
        // todo redis直接存了CommentDto对象，其中包括孩子的姓名和头像，但应该只存孩子的id，在另一个缓存中存孩子的信息
    }

    @Override
    public void addComment(long hotId, String content) {
        Kid kid = kidMapper.selectById(BaseInfoContext.get().getId());
        stringRedisTemplate.opsForList().leftPush(
                commentKeyPrefix + hotId,
                JSON.toJSONString(new CommentDto(content, kid.getName(), kid.getPhoto(), LocalDateTime.now())));
        // todo 其实可以在BaseContext中存下当前用户的基本信息，name、avatar等
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        updateCacheHot();
        updateCacheLike();
        updateCacheComment();
    }

    private void updateCacheHot() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("liked");
        filter.getExcludes().add("likeNum");
        List<HotReplyDto> hots = hotReplyMapper.selectAll();
        stringRedisTemplate.opsForValue().set(
                allKey,
                JSON.toJSONString(hots, filter));
        stringRedisTemplate.opsForValue().set(sizeKey, String.valueOf(hots.size()));
    }

    private void updateCacheLike() {
        List<ReplyHotLike> likes = replyHotLikeMapper.selectAll();
        for (ReplyHotLike like : likes)
            stringRedisTemplate.opsForSet().add(
                    likeKeyPrefix + like.getHotId(),
                    like.getKidId().toString());
    }

    private void updateCacheComment() {
        List<Comment> comments = hotReplyMapper.selectAllComments();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("hotId");
        Set<String> keys = stringRedisTemplate.keys(commentKeyPrefix + "*");
        if (keys != null) stringRedisTemplate.delete(keys);
        for (Comment comment : comments) {
            stringRedisTemplate.opsForList().leftPush(
                    commentKeyPrefix + comment.getHotId(),
                    JSON.toJSONString(comment, filter));
        }
    }

    @Transactional
    //        @Scheduled(cron = "* * * * * *")
    public void updateDB() {
        updateDbLike();
        updateDbComment();
    }

    private void updateDbLike() {
        Set<String> keys = stringRedisTemplate.keys(likeKeyPrefix + "*");
        if (keys == null || keys.isEmpty()) return;
        List<ReplyHotLike> likes = new ArrayList<>();
        for (String key : keys) {
            String hotId = key.substring(likeKeyPrefix.length() + 1);
            Set<String> kidIds = stringRedisTemplate.opsForSet().members(key);
            if (kidIds == null) continue;
            for (String kidId : kidIds)
                likes.add(new ReplyHotLike(Long.parseLong(hotId), Long.parseLong(kidId)));
        }
        replyHotLikeMapper.deleteAll();
        replyHotLikeMapper.insertAll(likes);
    }

    private void updateDbComment() {
        Set<String> keys = stringRedisTemplate.keys(commentKeyPrefix + "*");
        if (keys == null || keys.isEmpty()) return;
        List<ReplyHotComment> res = new ArrayList<>();
        for (String key : keys) {
            String hotId = key.substring(commentKeyPrefix.length() + 1);
            Long size = stringRedisTemplate.opsForList().size(key);
            if (size == null) continue;
            List<String> jsons = stringRedisTemplate.opsForList().range(key, 0, size);
            if (jsons == null) continue;
            List<ReplyHotComment> tmp = jsons.stream().map((json) -> JSON.parseObject(json, ReplyHotComment.class)).collect(Collectors.toList());
            // todo redis里还是应该只需要存 孩子id、评论内容、评论时间
            for (ReplyHotComment t : tmp) {
                t.setHotId(Long.valueOf(hotId));
                res.add(t);
            }
        }
        replyHotCommentMapper.deleteAll();
        replyHotCommentMapper.insertAll(res);
    }


}
