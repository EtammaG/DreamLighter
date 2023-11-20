package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.AwardType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AwardTypeMapper extends BaseMapper<AwardType> {

    List<AwardType> selectAll();
}
