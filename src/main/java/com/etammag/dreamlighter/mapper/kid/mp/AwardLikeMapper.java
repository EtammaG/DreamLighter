package com.etammag.dreamlighter.mapper.kid.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.kid.db.AwardLike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AwardLikeMapper extends BaseMapper<AwardLike> {

    Integer cat(AwardLike awardLike);
}
