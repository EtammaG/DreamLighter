package com.etammag.dreamlighter.mapper.donor;

import com.etammag.dreamlighter.entity.donor.ProjectSimDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Page
public interface ProjectInfoMapper {
    List<ProjectSimDto> selectSimByType(Long typeId);

    List<ProjectSimDto> selectSim();
}
