package com.etammag.dreamlighter.mapper.volunteer.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.volunteer.VolunMisDto;
import com.etammag.dreamlighter.entity.volunteer.db.VolunToMission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VolunToMissionMapper extends BaseMapper<VolunToMission> {

    VolunMisDto selectVolunMisDto(Long id);
}
