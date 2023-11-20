package com.etammag.dreamlighter.mapper.donor.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.donor.db.ProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectTypeMapper extends BaseMapper<ProjectType> {

    List<ProjectType> selectAll();
}
