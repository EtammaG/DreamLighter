package com.etammag.dreamlighter.mapper.donor.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.donor.db.Project;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @MapKey("id")
    Map<Long, Project> selectByIds(@Param("ids") List<Long> ids);
}
