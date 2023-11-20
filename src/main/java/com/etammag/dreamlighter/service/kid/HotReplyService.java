package com.etammag.dreamlighter.service.kid;


import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.HotReplyDto;

import java.util.List;

public interface HotReplyService {
    List<HotReplyDto> getAll();

    void like(long hotId);

    void unlike(long hotId);

    List<CommentDto> getComment(String hotId);

    void addComment(long hotId, String comment);

}
