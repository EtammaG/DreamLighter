package com.etammag.dreamlighter.mapper.volunteer;

import com.etammag.dreamlighter.entity.common.CommentDto;
import com.etammag.dreamlighter.entity.kid.KidVieDto;
import com.etammag.dreamlighter.entity.kid.db.Kid;
import com.etammag.dreamlighter.entity.mission.db.Mission;
import com.etammag.dreamlighter.entity.volunteer.ArticleDto;
import com.etammag.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@Page
public interface VolunteerStatisticMapper {

    /**
     * 查询该志愿者对应的的所有任务的分别提交总数
     *
     * @param volunId
     * @return
     */
    @MapKey("mission_id")
    Map<Long, Map<String, Object>> submitCount(Long volunId);

    /**
     * 查询该志愿者最新的任务信息
     *
     * @param volunId
     * @return
     */
    Mission volunRandomMission(Long volunId);

    /**
     * 查询所对应任务的（孩子已完成）的，老师已经批改的作业数
     *
     * @param
     * @return
     */
    @MapKey("mission_id")
    Map<Long, Map<String, Object>> hasCheck(List<Long> ids);

    @MapKey("mission_id")
    Map<Long, Map<String, Object>> hasCheckAll();

    /**
     * 查询该志愿者对应的所有任务
     *
     * @param volunId
     * @return
     */
    List<Mission> volunAllMission(Long volunId);

    List<KidVieDto> volunMissionKid(Long missionId);

    /**
     * 查询所有社区文章
     *
     * @param title
     * @return
     */
    List<ArticleDto> allArticle(String title);

    /**
     * 根据文章id查询文章的所有评论
     *
     * @param articleId
     * @return
     */
    List<CommentDto> articleComment(Long articleId);

    /**
     * 查询该志愿者所收藏的文章
     *
     * @param volunId
     * @return
     */
    List<ArticleDto> allArticleLike(Long volunId);

    /**
     * 查询该志愿者所对应的孩子
     *
     * @param volunId
     * @return
     */
    List<Kid> volunKid(Long volunId);


    /**
     * 查询出最近获的善款捐助的孩子
     *
     * @param kidId
     * @return
     */
    @MapKey("kid_id")
    List<Map<String, Object>> newDonation(List<Long> kidId);


    /**
     * 查询出最近获得物品捐助的孩子
     *
     * @param kidId
     * @return
     */
    @MapKey("kid_id")
    List<Map<String, Object>> newThingDonation(List<Long> kidId);

    /**
     * 查询该志愿者是否给该文章点赞
     *
     * @param volunId
     * @param articleId
     * @return
     */
    Long getIfLove(Long volunId, Long articleId);

    /**
     * 查询该文章所有点赞
     *
     * @param articleId
     * @return
     */
    Long loveCount(Long articleId);

    /**
     * 添加文章点赞
     *
     * @param volunId
     * @param articleId
     */
    void inputLove(Long volunId, Long articleId);

    /**
     * 取消文章点赞
     *
     * @param volunId
     * @param articleId
     */
    void deleteLove(Long volunId, Long articleId);


}
