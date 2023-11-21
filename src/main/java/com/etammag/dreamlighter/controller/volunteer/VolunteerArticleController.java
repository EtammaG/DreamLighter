package com.etammag.dreamlighter.controller.volunteer;

import com.etammag.icommon.entity.Result;
import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.dreamlighter.entity.volunteer.db.Article;
import com.etammag.dreamlighter.service.volunteer.VolunteerArticleService;
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
public class VolunteerArticleController {

    private final VolunteerArticleService volunteerArticleService;

    @Autowired
    public VolunteerArticleController(VolunteerArticleService volunteerArticleService) {
        this.volunteerArticleService = volunteerArticleService;
    }

    @GetMapping(value = "/random")
    @ApiOperation(value = "志愿者页面滑动的文章")
    public Result<ArticleDto> getRandomArticle() {
        return Result.success(volunteerArticleService.getRandomArticle());
    }

    @PostMapping(value = "/list", produces = "application/json; charset=utf-8")
    @ApiOperation("文章列表（分页）")
    public Result<IPageInfo<ArticleDto>> getAllArticle(@RequestBody Map<String, String> map) {
        IPage iPage = new IPage(
                Integer.parseInt(map.get("pageNum")),
                Integer.parseInt(map.get("pageSize")),
                map.get("orderBy"));
        return Result.success(volunteerArticleService.getAllArticle(iPage, map.get("title")));
    }

    @GetMapping(value = "/detail/{id}")
    @ApiOperation("一篇文章的详细信息")
    public Result<Article> getArticleDetail(@ApiParam(name = "id", value = "文章的id") @PathVariable String id) {
        return Result.success(volunteerArticleService.getArticleDetails(Long.parseLong(id)));
    }

    @GetMapping(value = "/detail/{id}/comment")
    @ApiOperation("一篇文章的评论")
    public Result<List<CommentDto>> getArticleDetailComment(@ApiParam(name = "id", value = "文章的id") @PathVariable String id) {
        return Result.success(volunteerArticleService.getArticleComments(Long.parseLong(id)));
    }

    @PostMapping(value = "/comment")
    @ApiOperation("被收藏的文章")
    public Result<IPageInfo<ArticleDto>> getCollectedArticle(@RequestBody IPage iPage) {
        return Result.success(volunteerArticleService.getArticleCollected(iPage));
    }

    @GetMapping(value = "/love")
    @ApiOperation("详细文章内容点赞")
    public Result<Boolean> ifLove(String articleId) {
        return Result.success(volunteerArticleService.getIfLove(articleId));
    }

    @GetMapping(value = "/lovecount")
    @ApiOperation("详细文章内容的点赞数")
    public Result<Integer> articleLoveCount(String articleId) {
        return Result.success(volunteerArticleService.getLoveCount(articleId));
    }

    @PostMapping(value = "/loveput/{articleId}")
    @ApiOperation("给文章点赞")
    public Result<String> putArticleLove(@PathVariable String articleId) {
        volunteerArticleService.putArticleLove(articleId);
        return Result.success("点赞成功");
    }

    @DeleteMapping(value = "/love/delete/{articleId}")
    @ApiOperation("取消点赞")
    public Result<String> deleteArticleLove(@PathVariable Long articleId) {
        volunteerArticleService.deleteArticleLove(articleId);
        return Result.success("取消成功");
    }

    @PostMapping(value = "/likeput/{articleId}")
    @ApiOperation("添加文章收藏")
    public Result<String> putArticleLike(@PathVariable String articleId) {
        volunteerArticleService.putArticleLike(Long.valueOf(articleId));
        return Result.success("收藏成功");
    }

}
