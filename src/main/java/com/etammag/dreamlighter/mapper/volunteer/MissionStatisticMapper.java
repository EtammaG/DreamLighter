package com.etammag.dreamlighter.mapper.volunteer;

import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.entity.mission.ReplyVieDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Page
public interface MissionStatisticMapper {

    List<MissionVolViewDto> selectVolViews(Long volunId);

    List<ReplyVieDto> selectReplyVie(Long missionId);

    MissionDto selectDtoById(Long missionId);
}
