package com.etammag.dreamlighter.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleComment;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleLike;
import com.etammag.dreamlighter.mapper.volunteer.VolunteerStatisticMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleCommentMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleLikeMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.VolunteerMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.volunteer.VolunteerArticleService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VolunteerArticleServiceImpl implements VolunteerArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleCommentMapper commentMapper;
    private final VolunteerMapper volunteerMapper;
    private final ArticleLikeMapper articleLikeMapper;
    private final VolunteerStatisticMapper volunteerStatisticMapper;

    @Autowired
    public VolunteerArticleServiceImpl(ArticleMapper articleMapper, ArticleCommentMapper commentMapper, VolunteerMapper volunteerMapper, ArticleLikeMapper articleLikeMapper, VolunteerStatisticMapper volunteerStatisticMapper) {
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.volunteerMapper = volunteerMapper;
        this.articleLikeMapper = articleLikeMapper;
        this.volunteerStatisticMapper = volunteerStatisticMapper;
    }

    @Override
    public ArticleDto getRandomArticle() {
        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        articleQuery.orderByDesc(Article::getArticleTime);

        List<Article> articles = articleMapper.selectList(articleQuery);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(articles.get(0), articleDto);

        Long articleId = articleDto.getId();
        LambdaQueryWrapper<ArticleComment> commentQuery = new LambdaQueryWrapper<>();
        commentQuery.eq(ArticleComment::getArticleId, articleId);
        int count = Math.toIntExact(commentMapper.selectCount(commentQuery));
        articleDto.setAmount(count);

        return articleDto;
    }

    @Override
    public IPageInfo<ArticleDto> getAllArticle(IPage iPage, String title) {
        return volunteerStatisticMapper.allArticleP(iPage, title == null ? "%%" : "%" + title + "%");
    }

    @Override
    public Article getArticleDetails(long id) {
        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        articleQuery.eq(Article::getId, id);
        return articleMapper.selectOne(articleQuery);
    }

    @Override
    public List<CommentDto> getArticleComments(long id) {
        return volunteerStatisticMapper.articleComment(id);
    }

    @Override
    public IPageInfo<ArticleDto> getArticleCollected(IPage iPage) {
        return volunteerStatisticMapper.allArticleLikeP(iPage, BaseInfoContext.get().getId());
    }

    @Override
    public Boolean getIfLove(String articleId) {
        long artId = Long.parseLong(articleId);
        Long volunId = BaseInfoContext.get().getId();
        Long ifLove = volunteerStatisticMapper.getIfLove(volunId, artId);
        return ifLove != 0L;
    }

    @Override
    public Integer getLoveCount(String articleId) {
        return volunteerStatisticMapper.loveCount(Long.parseLong(articleId)).intValue();
    }

    @Override
    public void putArticleLove(String articleId) {
        long artId = Long.parseLong(articleId);
        Long volunId = BaseInfoContext.get().getId();
        volunteerStatisticMapper.inputLove(volunId, artId);
    }

    @Override
    public void deleteArticleLove(Long articleId) {
        Long volunId = BaseInfoContext.get().getId();
        volunteerStatisticMapper.deleteLove(volunId, articleId);
    }

    @Override
    public void putArticleLike(Long articleId) {
        articleLikeMapper.insert(new ArticleLike(BaseInfoContext.get().getId(), articleId));

    }


}
