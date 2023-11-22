package com.etammag.dreamlighter.service.mission;


import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionKidViewDto;

import java.time.LocalDate;
import java.util.List;

public interface MissionInfoService {
    List<MissionKidViewDto> getKidViewByKidIdAndDate(Long kidId, LocalDate date);

    MissionDto getById(Long id);
}
