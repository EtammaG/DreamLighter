package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.List;

public interface VolunArticleService {

    List<Article> getRandomArticles();

    IPageInfo<Article> pageByTitle(IPage iPage, String title);

    ArticleDto getById(long id);

    List<CommentDto> getCommentById(Long id);

    void addComment(Long volunId, Long articleId, String content);


    IPageInfo<Article> pageFavors(IPage iPage, Long volunId);

    void addFavor(Long volunId, Long articleId);

    void delFavor(Long volunId, Long articleId);

    void addLike(Long volunId, Long articleId);

    void delLike(Long volunId, Long articleId);

}
