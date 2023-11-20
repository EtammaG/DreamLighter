package com.etammag.dreamlighter.mapper.kid;

import com.etammag.dreamlighter.entity.kid.db.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyInfoMapper {

    Reply selectByKidIdAndMissionId(@Param("kidId") Long id, @Param("missionId") String missionId);

}
