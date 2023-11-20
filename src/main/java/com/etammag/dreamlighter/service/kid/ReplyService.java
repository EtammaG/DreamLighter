package com.etammag.dreamlighter.service.kid;

import com.etammag.dreamlighter.entity.kid.db.Reply;

public interface ReplyService {
    Reply getByKidIdAndMissionId(String missionId);

    void add(Long missionId, String replyMedia);
}
