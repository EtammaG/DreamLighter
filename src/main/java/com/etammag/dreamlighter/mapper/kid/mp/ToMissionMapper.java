package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.ToMission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToMissionMapper extends BaseMapper<ToMission> {

    void updateReply(ToMission toMission);

}
