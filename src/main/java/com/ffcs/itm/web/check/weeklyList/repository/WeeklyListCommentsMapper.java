package com.ffcs.itm.web.check.weeklyList.repository;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListComment;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WeeklyListCommentsMapper {

    /**
     * 查询
     * @param weeklyId
     * @return
     */
    public List<CheckWeeklyListComment> selectAllCommentsInId(@Param("weeklyId") Integer weeklyId);
    public StaffUser selectDetailStaff(@Param("staffId") Integer staffId);

    public List<CheckWeeklyListComment> selectAllCommentsChridren(@Param("weeklyId") Integer weeklyId, @Param("commentId") Integer commentId, @Param("commentParentId") Integer commentParentId);

    public List<CheckWeeklyListComment> selectAllCommentsChridren2(@Param("weeklyId") Integer weeklyId, @Param("commentId") Integer commentId);


    /**
     * 添加一级评论
     */
    public boolean insertTopComment(@Param("cwlc") CheckWeeklyListComment cwlc);

    /**
     * 一级评论下的回复
     * @param cwlc
     * @return
     */
    public boolean insertCommentBack(@Param("cwlc") CheckWeeklyListComment cwlc);

    /**
     * 评论列表删除评论
     * @param commentId
     * @return
     */
    public boolean delOneComments(@Param("commentId") Integer commentId);
    /**
     * 周报删除，周报下的所有评论删除
     */
    public boolean delCommentsByWeekly(@Param("weeklyId")Integer weeklyId);

    /**
     * 好评率
     */
    public Double selectOneAvg(@Param("weeklyId")Integer weekyId);

    /**
     * selectByParentId
     */
    public CheckWeeklyListComment selectByParentId(@Param("commentParentId")Integer commentParentId);
}
