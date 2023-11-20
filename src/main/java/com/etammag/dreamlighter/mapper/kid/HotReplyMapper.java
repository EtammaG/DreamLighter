package com.etammag.dreamlighter.mapper.kid;

import com.etammag.dreamlighter.entity.common.Comment;
import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.HotReplyDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Page
public interface HotReplyMapper {
    List<HotReplyDto> selectAll();

    List<CommentDto> selectCommentsByHotId(String hotId);

    List<Comment> selectAllComments();
}
