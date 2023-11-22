package com.etammag.dreamlighter.mapper.volunteer.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleLove;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleLoveMapper extends BaseMapper<ArticleLove> {

    void delete(ArticleLove articleLove);

}
