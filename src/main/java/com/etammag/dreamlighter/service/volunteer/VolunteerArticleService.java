package com.etammag.dreamlighter.service.volunteer;


import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;

import java.util.List;

public interface VolunteerArticleService {
    ArticleDto getRandomArticle();

    IPageInfo<ArticleDto> getAllArticle(IPage iPage, String title);

    Article getArticleDetails(long id);

    List<CommentDto> getArticleComments(long id);

    IPageInfo<ArticleDto> getArticleCollected(IPage iPage);

    Boolean getIfLove(String articleId);

    Integer getLoveCount(String articleId);

    void putArticleLove(String articleId);

    void deleteArticleLove(Long articleId);

    void putArticleLike(Long articleId);
}
