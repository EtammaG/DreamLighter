package com.etammag.dreamlighter.service.volunteer.impl;

import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.entity.mission.ReplyVieDto;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyMapper;
import com.etammag.dreamlighter.mapper.volunteer.MissionStatisticMapper;
import com.etammag.dreamlighter.service.volunteer.VolunMissionService;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunMissionServiceImpl implements VolunMissionService {

    private final MissionStatisticMapper missionStatisticMapper;

    private final ReplyMapper replyMapper;

    @Autowired
    public VolunMissionServiceImpl(MissionStatisticMapper missionStatisticMapper, ReplyMapper replyMapper) {
        this.missionStatisticMapper = missionStatisticMapper;
        this.replyMapper = replyMapper;
    }

    @Override
    public IPageInfo<MissionVolViewDto> pageAll(IPage iPage) {
        return missionStatisticMapper.selectVolViewsP(iPage, BaseInfoContext.get().getId());
    }

    @Override
    public IPageInfo<ReplyVieDto> getReplyVie(IPage iPage, Long missionId) {
        return missionStatisticMapper.selectReplyVieP(iPage, missionId);
    }

    @Override
    public MissionDto getMissionDto(Long missionId) {
        return missionStatisticMapper.selectDtoById(missionId);
    }

    @Override
    public void score(Reply reply) {
        replyMapper.insertScore(reply);
    }


}
