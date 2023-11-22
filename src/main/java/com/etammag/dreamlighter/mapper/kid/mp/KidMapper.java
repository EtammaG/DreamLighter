package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@Page
public interface KidMapper extends BaseMapper<Kid> {

    @MapKey("id")
    Map<Long, Kid> selectByIds(List<Long> ids);

    List<Kid> selectRandom(int num);

    List<Kid> selectByVolunId(Long volunId);
}
