package com.etammag.dreamlighter.mapper.kid;

import com.etammag.dreamlighter.entity.kid.KidMalDto;
import com.etammag.dreamlighter.entity.kid.KidSimDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@Page
public interface KidInfoMapper {
    List<KidSimDto> selectSimByTypeId(Long typeId);

    List<Map<String, Object>> selectMisInfo(Long id);

    KidMalDto selectMalInfo(Long id);

    Integer selectRankById(Long id);

    Integer selectWeekrankById(Long id);

    List<KidSimDto> selectSim();

}
