package com.etammag.dreamlighter.mapper.volunteer.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleLikeMapper extends BaseMapper<ArticleLike> {

    Integer catLike(@Param("volunId") Long volunId, @Param("articleId") Long articleId);

    void delete(ArticleLike articleLike);
}
