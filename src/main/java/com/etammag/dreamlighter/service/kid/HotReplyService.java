package com.etammag.dreamlighter.service.kid;


import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.HotReplyDto;

import java.util.List;

public interface HotReplyService {
    List<HotReplyDto> getAll(Long kidId);

    void like(Long kidId, long hotId);

    void unlike(Long kidId, long hotId);

    List<CommentDto> getComment(String hotId);

    void addComment(Long kidId, long hotId, String comment);

}
