package com.etammag.dreamlighter.service.mission.impl;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionKidViewDto;
import com.etammag.dreamlighter.entity.mission.db.Mission;
import com.etammag.dreamlighter.mapper.mission.MissionInfoMapper;
import com.etammag.dreamlighter.mapper.mission.mp.MissionMapper;
import com.etammag.dreamlighter.common.BaseInfoContext;
import com.etammag.dreamlighter.service.mission.MissionInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MissionInfoServiceImpl implements MissionInfoService {

    private final MissionInfoMapper missionInfoMapper;
    private final MissionMapper missionMapper;

    @Autowired
    public MissionInfoServiceImpl(MissionInfoMapper missionInfoMapper, MissionMapper missionMapper) {
        this.missionInfoMapper = missionInfoMapper;
        this.missionMapper = missionMapper;
    }

    @Override
    public List<MissionKidViewDto> getKidViewByKidIdAndDate(LocalDate date) {
        List<Map<String, Object>> maps = missionInfoMapper.selectKidViewByKidIdAndDate(BaseInfoContext.get().getId(), date);
        List<MissionKidViewDto> res = new ArrayList<>(maps.size());
        for(Map<String, Object> map : maps) {
            MissionKidViewDto missionKidViewDto = JSON.parseObject(JSON.toJSONString(map), MissionKidViewDto.class);
            missionKidViewDto.setDone(map.get("reply_id") != null);
            missionKidViewDto.setChecked(map.get("score") != null);
            res.add(missionKidViewDto);
        }
        return res;
    }

    @Override
    public MissionDto getById(Long id) {
        Mission mission = missionMapper.selectById(id);
        List<Long> replyIds = missionInfoMapper.selectReplyIds(id);
        int count = 0;
        for(Long replyId : replyIds)
            if(replyId != null) count++;
        MissionDto missionDto = new MissionDto();
        BeanUtils.copyProperties(mission, missionDto);
        missionDto.setTotalNum(replyIds.size());
        missionDto.setDoneNum(count);
        return missionDto;
    }
}
