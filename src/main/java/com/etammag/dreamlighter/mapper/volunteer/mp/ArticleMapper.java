package com.etammag.dreamlighter.mapper.volunteer.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Page
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectRandoms(int num);

    List<Article> selectAllByTitle(String title);
}
