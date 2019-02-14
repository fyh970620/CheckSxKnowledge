package com.ffcs.itm.web.check.weeklyList.entity;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CheckWeeklyListComment implements Serializable{

    private Integer commentId;                //主键ID
    private Integer commentParentId;         //评论回复归于哪个评论ID
    private String comment;                    //评论内容
    private Integer commentGrade;             //评论分数  *NUMBER(9,2)
    private Date commentDate;                 //评论时间
    private Integer commentStaffId;          //评论人id
    private Integer weeklyId;                 //周报ID
    private String time;
    private Integer od =0;

    private StaffUser parentUser;    //上一级的user

    //附加属性
    private StaffUser staff;
    private List<CheckWeeklyListComment> children;

    @Override
    public String toString() {
        return "CheckWeeklyListComment{" +
                "commentId=" + commentId +
                ", commentParentId=" + commentParentId +
                ", comment='" + comment + '\'' +
                ", commentGrade=" + commentGrade +
                ", commentDate=" + commentDate +
                ", commentStaffId=" + commentStaffId +
                ", weeklyId=" + weeklyId +
                ", time='" + time + '\'' +
                ", od=" + od +
                ", parentUser=" + parentUser +
                ", staff=" + staff +
                ", children=" + children +
                '}';
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(Integer commentGrade) {
        this.commentGrade = commentGrade;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentStaffId() {
        return commentStaffId;
    }

    public void setCommentStaffId(Integer commentStaffId) {
        this.commentStaffId = commentStaffId;
    }

    public Integer getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public List<CheckWeeklyListComment> getChildren() {
        return children;
    }

    public void setChildren(List<CheckWeeklyListComment> children) {
        this.children = children;
    }

    public StaffUser getParentUser() {
        return parentUser;
    }

    public void setParentUser(StaffUser parentUser) {
        this.parentUser = parentUser;
    }
}
