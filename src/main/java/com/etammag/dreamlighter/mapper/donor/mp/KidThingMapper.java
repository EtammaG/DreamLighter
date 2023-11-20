package com.etammag.dreamlighter.mapper.donor.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.donor.db.KidThing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KidThingMapper extends BaseMapper<KidThing> {

    KidThing selectOneByKidId(Long kidId);

}
