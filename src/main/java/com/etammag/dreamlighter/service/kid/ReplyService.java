package com.etammag.dreamlighter.service.kid;

import com.etammag.dreamlighter.entity.kid.db.Reply;

public interface ReplyService {
    Reply getByKidIdAndMissionId(Long kidId, String missionId);

    void add(Long kidId, Long missionId, String replyMedia);
}
