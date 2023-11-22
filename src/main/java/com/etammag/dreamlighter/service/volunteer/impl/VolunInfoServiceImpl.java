package com.etammag.dreamlighter.service.volunteer.impl;

import com.etammag.dreamlighter.entity.volunteer.VolunMisDto;
import com.etammag.dreamlighter.entity.volunteer.db.Volunteer;
import com.etammag.dreamlighter.mapper.volunteer.mp.VolunToMissionMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.VolunteerMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.volunteer.VolunInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunInfoServiceImpl implements VolunInfoService {

    private final VolunteerMapper volunteerMapper;
    private final VolunToMissionMapper volunToMissionMapper;

    @Autowired
    public VolunInfoServiceImpl(VolunteerMapper volunteerMapper, VolunToMissionMapper volunToMissionMapper) {
        this.volunteerMapper = volunteerMapper;
        this.volunToMissionMapper = volunToMissionMapper;
    }

    @Override
    public Volunteer getVolunteerInfo() {
        return volunteerMapper.selectById(BaseInfoContext.get().getId());
    }

    @Override
    public VolunMisDto getVolunMisInfo() {
        return volunToMissionMapper.selectVolunMisDto(BaseInfoContext.get().getId());
    }
}
