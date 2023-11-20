package com.etammag.dreamlighter.service.kid.impl;

import com.etammag.dreamlighter.common.utils.IdWorker;
import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.kid.db.ToMission;
import com.etammag.dreamlighter.mapper.kid.ReplyInfoMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ToMissionMapper;
import com.etammag.dreamlighter.common.BaseInfoContext;
import com.etammag.dreamlighter.service.kid.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyInfoMapper replyInfoMapper;
    private final ReplyMapper replyMapper;
    private final ToMissionMapper toMissionMapper;

    @Autowired
    public ReplyServiceImpl(ReplyInfoMapper replyInfoMapper, ReplyMapper replyMapper, ToMissionMapper toMissionMapper) {
        this.replyInfoMapper = replyInfoMapper;
        this.replyMapper = replyMapper;
        this.toMissionMapper = toMissionMapper;
    }

    @Override
    public Reply getByKidIdAndMissionId(String missionId) {
        return replyInfoMapper.selectByKidIdAndMissionId(BaseInfoContext.get().getId(), missionId);
    }

    @Override
    public void add(Long missionId, String replyMedia) {
        Long replyId = IdWorker.nextId();
        replyMapper.insert(new Reply(replyId, replyMedia, null, null));
        toMissionMapper.updateReply(new ToMission(BaseInfoContext.get().getId(), missionId, replyId));
    }
}
