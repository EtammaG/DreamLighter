package com.etammag.dreamlighter.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.dreamlighter.entity.volunteer.db.Volunteer;
import com.etammag.dreamlighter.mapper.volunteer.mp.VolunteerMapper;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.dreamlighter.service.volunteer.VolunteerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerInfoServiceImpl implements VolunteerInfoService {

    private final VolunteerMapper volunteerMapper;

    @Autowired
    public VolunteerInfoServiceImpl(VolunteerMapper volunteerMapper) {
        this.volunteerMapper = volunteerMapper;
    }

    @Override
    public Volunteer getVolunteerInfo() {
        LambdaQueryWrapper<Volunteer> volunQuery = new LambdaQueryWrapper<>();
        volunQuery.eq(Volunteer::getId, BaseInfoContext.get().getId());
        return volunteerMapper.selectOne(volunQuery);
    }
}
