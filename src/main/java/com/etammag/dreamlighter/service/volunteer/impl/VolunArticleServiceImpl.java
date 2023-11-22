package com.etammag.dreamlighter.service.volunteer.impl;

import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleComment;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleLike;
import com.etammag.dreamlighter.entity.volunteer.db.ArticleLove;
import com.etammag.dreamlighter.mapper.volunteer.ArticleDetailMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleCommentMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleLikeMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleLoveMapper;
import com.etammag.dreamlighter.mapper.volunteer.mp.ArticleMapper;
import com.etammag.dreamlighter.service.volunteer.VolunArticleService;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class VolunArticleServiceImpl implements VolunArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleLikeMapper articleLikeMapper;
    private final ArticleLoveMapper articleLoveMapper;
    private final ArticleCommentMapper commentMapper;

    private final ArticleDetailMapper articleDetailMapper;

    @Autowired
    public VolunArticleServiceImpl(ArticleMapper articleMapper, ArticleLikeMapper articleLikeMapper, ArticleLoveMapper articleLoveMapper, ArticleCommentMapper commentMapper, ArticleDetailMapper articleDetailMapper) {
        this.articleMapper = articleMapper;
        this.articleLikeMapper = articleLikeMapper;
        this.articleLoveMapper = articleLoveMapper;
        this.commentMapper = commentMapper;
        this.articleDetailMapper = articleDetailMapper;
    }

    @Autowired


    @Override
    public List<Article> getRandomArticles() {
        return articleMapper.selectRandoms(5);
    }

    @Override
    public IPageInfo<Article> pageByTitle(IPage iPage, String title) {
        return articleMapper.selectAllByTitleP(iPage, title == null ? "%%" : "%" + title + "%");
    }

    @Override
    public ArticleDto getById(long id) {
        return toDetail(articleMapper.selectById(id));
    }

    private ArticleDto toDetail(Long volunId, Article article) {
        Map<String, Object> det =  articleDetailMapper.selectDetById(article.getId());
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article, articleDto);
        articleDto.setLikeNum((Integer) det.get("likeNum"));
        articleDto.setCommentNum((Integer) det.get("commentNum"));
        articleDto.setLiked(articleLikeMapper.catLike(volunId, article.getId()) != null);
        return articleDto;
    }

    @Override
    public List<CommentDto> getCommentById(Long id) {
        return articleDetailMapper.selectComments(id);
    }

    @Override
    public void addComment(Long volunId, Long articleId, String content) {
        commentMapper.insert(new ArticleComment(articleId, volunId, content, LocalDateTime.now()));
    }

    @Override
    public IPageInfo<Article> pageFavors(IPage iPage, Long volunId) {
        return articleDetailMapper.selectFavorsByVolunIdP(iPage, volunId);
    }

    @Override
    public void addFavor(Long volunId, Long articleId) {
        articleLoveMapper.insert(new ArticleLove(volunId, articleId));
    }

    @Override
    public void delFavor(Long volunId, Long articleId) {
        articleLoveMapper.delete(new ArticleLove(volunId, articleId));
    }

    @Override
    public void addLike(Long volunId, Long articleId) {
        articleLikeMapper.insert(new ArticleLike(volunId, articleId));
    }

    @Override
    public void delLike(Long volunId, Long articleId) {
        articleLikeMapper.delete(new ArticleLike(volunId, articleId));
    }

}
