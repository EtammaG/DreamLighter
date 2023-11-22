package com.etammag.dreamlighter.mapper.volunteer;

import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@Page
public interface ArticleDetailMapper {
    Map<String, Object> selectDetById(Long id);

    List<CommentDto> selectComments(Long id);

    List<Article> selectFavorsByVolunId(Long id);


}
