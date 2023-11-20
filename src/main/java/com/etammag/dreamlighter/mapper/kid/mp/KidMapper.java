package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface KidMapper extends BaseMapper<Kid> {

    @MapKey("id")
    Map<Long, Kid> selectByIds(List<Long> ids);

    List<Kid> selectRandom(int num);
}
