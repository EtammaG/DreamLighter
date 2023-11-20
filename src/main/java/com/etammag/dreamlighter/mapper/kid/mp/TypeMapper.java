package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> selectAll();
}
