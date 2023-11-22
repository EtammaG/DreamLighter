package com.etammag.dreamlighter.service.kid.impl;

import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.kid.db.ToMission;
import com.etammag.dreamlighter.mapper.kid.ReplyInfoMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ToMissionMapper;
import com.etammag.dreamlighter.service.kid.ReplyService;
import com.etammag.icommon.utils.IdWorker;
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
    public Reply getByKidIdAndMissionId(Long kidId, String missionId) {
        return replyInfoMapper.selectByKidIdAndMissionId(kidId, missionId);
    }

    @Override
    public void add(Long kidId, Long missionId, String replyMedia) {
        Long replyId = IdWorker.nextId();
        replyMapper.insert(new Reply(replyId, replyMedia, null, null));
        toMissionMapper.updateReply(new ToMission(kidId, missionId, replyId));
    }
}
