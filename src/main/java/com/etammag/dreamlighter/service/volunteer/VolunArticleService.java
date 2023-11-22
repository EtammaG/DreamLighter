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

    void addComment(Long articleId, String content);


    IPageInfo<Article> pageFavors(IPage iPage);

    void addFavor(Long articleId);

    void delFavor(Long articleId);

    void addLike(Long articleId);

    void delLike(Long articleId);

}
