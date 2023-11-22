package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.dreamlighter.service.volunteer.VolunArticleService;
import com.etammag.icommon.context.BaseInfoContext;
import com.etammag.icommon.entity.Result;
import com.etammag.pagehelper.IPage;
import com.etammag.pagehelper.IPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteer/article")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端文章相关信息接口")
public class VolunArticleController {

    private final VolunArticleService volunArticleService;

    @Autowired
    public VolunArticleController(VolunArticleService volunArticleService) {
        this.volunArticleService = volunArticleService;
    }

    @GetMapping(value = "/random")
    @ApiOperation(value = "志愿者页面滑动的文章")
    public Result<List<Article>> getRandomArticle() {
        return Result.success(volunArticleService.getRandomArticles());
    }

    @PostMapping(value = "/list")
    @ApiOperation("文章列表（分页）")
    public Result<IPageInfo<Article>> getAllArticle(@RequestBody Map<String, String> map) {
        IPage iPage = new IPage(
                Integer.parseInt(map.get("pageNum")),
                Integer.parseInt(map.get("pageSize")),
                map.get("orderBy"));
        return Result.success(volunArticleService.pageByTitle(iPage, map.get("title")));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("一篇文章的详细信息")
    public Result<ArticleDto> getArticleDetail(@ApiParam(name = "id", value = "文章的id") @PathVariable String id) {
        return Result.success(volunArticleService.getById(Long.parseLong(id)));
    }

    @GetMapping(value = "/{id}/comment")
    @ApiOperation("一篇文章的评论")
    public Result<List<CommentDto>> getArticleDetailComment(@ApiParam(name = "id", value = "文章的id") @PathVariable String id) {
        return Result.success(volunArticleService.getCommentById(Long.parseLong(id)));
    }

    @PostMapping(value = "/comment")
    @ApiOperation("发布评论")
    public Result<Object> getCollectedArticle(@RequestBody Map<String, String> map) {
        volunArticleService.addComment(BaseInfoContext.get().getId(), Long.parseLong(map.get("articleId")), map.get("content"));
        return Result.success();
    }

    @GetMapping(value = "/favorite")
    @ApiOperation("被收藏的文章")
    public Result<IPageInfo<Article>> getCollectedArticle(IPage iPage) {
        return Result.success(volunArticleService.pageFavors(iPage, BaseInfoContext.get().getId()));
    }

    @PostMapping(value = "/favorite/{articleId}")
    @ApiOperation("添加文章收藏")
    public Result<String> putArticleFavorite(@PathVariable String articleId) {
        volunArticleService.addFavor(BaseInfoContext.get().getId(), Long.valueOf(articleId));
        return Result.success();
    }

    @DeleteMapping(value = "/favorite/{articleId}")
    @ApiOperation("取消收藏")
    public Result<String> deleteArticleFavorite(@PathVariable String articleId) {
        volunArticleService.delFavor(BaseInfoContext.get().getId(), Long.parseLong(articleId));
        return Result.success();
    }

    @PostMapping(value = "/like/{articleId}")
    @ApiOperation("给文章点赞")
    public Result<String> putArticleLove(@PathVariable String articleId) {
        volunArticleService.addLike(BaseInfoContext.get().getId(), Long.parseLong(articleId));
        return Result.success();
    }

    @DeleteMapping(value = "/like/{articleId}")
    @ApiOperation("取消点赞")
    public Result<String> deleteArticleLove(@PathVariable String articleId) {
        volunArticleService.delLike(BaseInfoContext.get().getId(), Long.parseLong(articleId));
        return Result.success("取消成功");
    }

}
