package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.ReplyHotComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyHotCommentMapper extends BaseMapper<ReplyHotComment> {

    List<ReplyHotComment> selectAll();

    void insertAll(List<ReplyHotComment> comments);

    void deleteAll();

}
