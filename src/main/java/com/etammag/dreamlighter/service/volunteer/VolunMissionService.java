package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.kid.db.Reply;
import com.etammag.dreamlighter.entity.mission.MissionDto;
import com.etammag.dreamlighter.entity.mission.MissionVolViewDto;
import com.etammag.dreamlighter.entity.mission.ReplyVieDto;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

public interface VolunMissionService {

    IPageInfo<MissionVolViewDto> pageAll(IPage iPage);

    IPageInfo<ReplyVieDto> getReplyVie(IPage iPage, Long missionId);

    MissionDto getMissionDto(Long missionId);

    void score(Reply reply);
}
