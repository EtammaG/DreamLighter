package com.etammag.dreamlighter.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.dreamlighter.entity.kid.KidVieDto;
import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.kid.db.ToMission;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.entity.mission.db.Mission;
import com.etammag.dreamlighter.mapper.kid.mp.ReplyMapper;
import com.etammag.dreamlighter.mapper.kid.mp.ToMissionMapper;
import com.etammag.dreamlighter.mapper.mission.mp.MissionMapper;
import com.etammag.dreamlighter.mapper.volunteer.VolunteerStatisticMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.volunteer.VolunteerKidService;
import com.etammag.dreamlighter.service.volunteer.VolunteerMissionService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VolunteerMissionServiceImpl implements VolunteerMissionService {
    private final MissionMapper missionMapper;
    private final VolunteerKidService volunteerKidService;
    private final ToMissionMapper toMissionMapper;
    private final ReplyMapper replyMapper;
    private final VolunteerStatisticMapper volunteerStatisticMapper;

    public VolunteerMissionServiceImpl(MissionMapper missionMapper, VolunteerKidService volunteerKidService, ToMissionMapper toMissionMapper, ReplyMapper replyMapper, VolunteerStatisticMapper volunteerStatisticMapper) {
        this.missionMapper = missionMapper;
        this.volunteerKidService = volunteerKidService;
        this.toMissionMapper = toMissionMapper;
        this.replyMapper = replyMapper;
        this.volunteerStatisticMapper = volunteerStatisticMapper;
    }

    @Override
    public MissionVolViewDto getRandomMission() {
        MissionVolViewDto missionVolViewDto = new MissionVolViewDto();
        Long volunId = BaseInfoContext.get().getId();

        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Mission missionNew = volunteerStatisticMapper.volunRandomMission(volunId);
        String randomTitle = missionNew.getTitle();
        Long randomSubmitTotal = (Long) missionSubmitCount.get(missionNew.getId()).get("count");
        int randomSubmitTotalInt = randomSubmitTotal.intValue();

        Map<Long, Map<String, Object>> hasCheck = volunteerStatisticMapper.hasCheckAll();
        Long hasCheckCount = (Long) hasCheck.get(missionNew.getId()).get("count");
        int hasCheckCountInt = hasCheckCount.intValue();

        int waiting = randomSubmitTotalInt - hasCheckCountInt;

        missionVolViewDto.setTitle(randomTitle);
        missionVolViewDto.setTotalSubmit(randomSubmitTotalInt);
        missionVolViewDto.setWaitingCheck(waiting);

        return missionVolViewDto;
    }

    @Override
    public IPageInfo<MissionVolViewDto> getAllMission(IPage iPage) {
        Long volunId = BaseInfoContext.get().getId();
        List<Mission> missions = volunteerStatisticMapper.volunAllMission(volunId);
        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Map<String, Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheckAll();
        IPageInfo<MissionVolViewDto> res = new IPageInfo<>();
        List<MissionVolViewDto> missionVolViewDtos = missions.stream().map(mission -> {
            Long missionId = mission.getId();
            Long total = (Long) missionSubmitCount.get(missionId).get("count");
            Integer totalInt = total.intValue();
            Long hasCheckCount = (Long) hasCheckMissionCount.get(missionId).get("count");
            Integer hasCheckCountInt = hasCheckCount.intValue();
            String title = mission.getTitle();
            MissionVolViewDto missionDto = new MissionVolViewDto();
            missionDto.setTitle(title);
            missionDto.setTotalSubmit(totalInt);
            missionDto.setWaitingCheck(totalInt - hasCheckCountInt);
            missionDto.setId(missionId);
            return missionDto;

        }).collect(Collectors.toList());
        res.setList(missionVolViewDtos);
        return res;
    }

    @Override
    public IPageInfo<KidVieDto> getKidInfo(IPage iPage, Long missionId) {
        return volunteerStatisticMapper.volunMissionKidP(iPage, missionId);
    }

    @Override
    public MissionDto getMissionDetail(long missionId) {
        LambdaQueryWrapper<Mission> missionQuery = new LambdaQueryWrapper<>();
        missionQuery.eq(Mission::getId, missionId);
        Mission mission = missionMapper.selectOne(missionQuery);
        Long volunId = BaseInfoContext.get().getId();
        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Map<String, Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheckAll();
        MissionDto missionDto = new MissionDto();
        BeanUtils.copyProperties(mission, missionDto);
        Long total = (Long) missionSubmitCount.get(missionId).get("count");
        Integer totalInt = total.intValue();
        missionDto.setTotalNum(totalInt);
        Long done = (Long) hasCheckMissionCount.get(missionId).get("count");
        missionDto.setDoneNum(done.intValue());
        return missionDto;
    }

    @Override
    public void putScore(Long missionId, Long kidId, int score) {
        LambdaQueryWrapper<ToMission> toMissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        toMissionLambdaQueryWrapper.eq(ToMission::getMissionId, missionId);
        toMissionLambdaQueryWrapper.eq(ToMission::getKidId, kidId);
        ToMission toMission = toMissionMapper.selectOne(toMissionLambdaQueryWrapper);
        Long replyId = toMission.getReplyId();
        Reply reply = new Reply();
        reply.setId(replyId);
        reply.setScore(score);
        replyMapper.updateById(reply);
    }

    @Override
    public Map<String, Long> getMissionTatal() {
        Long volunId = BaseInfoContext.get().getId();
        List<Mission> missions = volunteerStatisticMapper.volunAllMission(volunId);
        List<Long> missionList = missions.stream().map(Mission::getId).collect(Collectors.toList());
        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Map<String, Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheck(missionList);
        Long total = 0L;
        for (Long key : missionSubmitCount.keySet())
            total += (Long) missionSubmitCount.get(key).get("count");
        Long has = 0L;
        for (Long key : hasCheckMissionCount.keySet())
            has += (Long) hasCheckMissionCount.get(key).get("count");
        Map<String, Long> map = new HashMap<>();
        map.put("total", total);
        map.put("has", has);
        return map;
    }

}
