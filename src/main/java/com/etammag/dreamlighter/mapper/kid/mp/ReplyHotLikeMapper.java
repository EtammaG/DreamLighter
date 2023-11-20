package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.ReplyHotLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyHotLikeMapper extends BaseMapper<ReplyHotLike> {

    Integer cat(ReplyHotLike replyHotLike);

    int delete(ReplyHotLike replyHotLike);

    List<ReplyHotLike> selectAll();

    void deleteAll();

    void insertAll(List<ReplyHotLike> replies);
}
