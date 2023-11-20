package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.kid.KidVieDto;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.Map;

public interface VolunteerMissionService {

    MissionVolViewDto getRandomMission();

    IPageInfo<MissionVolViewDto> getAllMission(IPage iPage);

    IPageInfo<KidVieDto> getKidInfo(IPage iPage, Long missionId);

    MissionDto getMissionDetail(long missionId);

    void putScore(Long missionId, Long kidId, int score);

    Map<String, Long> getMissionTatal();
}
