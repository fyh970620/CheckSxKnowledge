package com.ffcs.itm.web.check.entity;

import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListAttach;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListComment;

import java.io.Serializable;
import java.util.List;

public class CheckWeeklyList implements Serializable{

    private Integer weeklyId;          //'周报主键ID';
    private String subjectName;        //'技能名称'
    private Integer createStaffId;    //'创建人ID'
    private String subjectClass;       //'哪一届新人'
    private String createDate;           //'创建时间'
    private String weeklyDesc;         //'本周周报描述'
    private String nextWeeklyDesc;    //'下周学习计划'
    private String subjectGroupType;  //'人员归属组分类1前台2后台3工程'
    private String keyWord;            //'关键字标签'
    private String subjectNote;          //'周报笔记'
    private String staffType;          //'区分是2社招还是1实习生'
    private String weeklyType;         //周报类型123456

    //附加属性
    private List<CheckWeeklyListAttach> cwlas;    //周报 --> 多附件
    private List<CheckWeeklyListComment> cwlcs;   //周报 --> 多评论
    private StaffUser staff;
    private String subjectGroupTypeValue;
    private String staffTypeValue;
    private String weeklyTypeValue;

    @Override
    public String toString() {
        return "CheckWeeklyList{" +
                "weeklyId=" + weeklyId +
                ", subjectName='" + subjectName + '\'' +
                ", createStaffId=" + createStaffId +
                ", subjectClass='" + subjectClass + '\'' +
                ", createDate='" + createDate + '\'' +
                ", weeklyDesc='" + weeklyDesc + '\'' +
                ", nextWeeklyDesc='" + nextWeeklyDesc + '\'' +
                ", subjectGroupType='" + subjectGroupType + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", subjectNote='" + subjectNote + '\'' +
                ", staffType='" + staffType + '\'' +
                ", weeklyType='" + weeklyType + '\'' +
                ", cwlas=" + cwlas +
                ", cwlcs=" + cwlcs +
                ", staff=" + staff +
                ", subjectGroupTypeValue='" + subjectGroupTypeValue + '\'' +
                ", staffTypeValue='" + staffTypeValue + '\'' +
                ", weeklyTypeValue='" + weeklyTypeValue + '\'' +
                '}';
    }

    public String getWeeklyType() {
        return weeklyType;
    }

    public void setWeeklyType(String weeklyType) {
        this.weeklyType = weeklyType;
    }

    public String getSubjectGroupTypeValue() {
        return subjectGroupTypeValue;
    }

    public void setSubjectGroupTypeValue(String subjectGroupTypeValue) {
        this.subjectGroupTypeValue = subjectGroupTypeValue;
    }

    public String getStaffTypeValue() {
        return staffTypeValue;
    }

    public void setStaffTypeValue(String staffTypeValue) {
        this.staffTypeValue = staffTypeValue;
    }

    public String getWeeklyTypeValue() {
        return weeklyTypeValue;
    }

    public void setWeeklyTypeValue(String weeklyTypeValue) {
        this.weeklyTypeValue = weeklyTypeValue;
    }

    public Integer getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(Integer createStaffId) {
        this.createStaffId = createStaffId;
    }

    public String getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getWeeklyDesc() {
        return weeklyDesc;
    }

    public void setWeeklyDesc(String weeklyDesc) {
        this.weeklyDesc = weeklyDesc;
    }

    public String getNextWeeklyDesc() {
        return nextWeeklyDesc;
    }

    public void setNextWeeklyDesc(String nextWeeklyDesc) {
        this.nextWeeklyDesc = nextWeeklyDesc;
    }

    public String getSubjectGroupType() {
        return subjectGroupType;
    }

    public void setSubjectGroupType(String subjectGroupType) {
        this.subjectGroupType = subjectGroupType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSubjectNote() {
        return subjectNote;
    }

    public void setSubjectNote(String subjectNote) {
        this.subjectNote = subjectNote;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public List<CheckWeeklyListAttach> getCwlas() {
        return cwlas;
    }

    public void setCwlas(List<CheckWeeklyListAttach> cwlas) {
        this.cwlas = cwlas;
    }

    public List<CheckWeeklyListComment> getCwlcs() {
        return cwlcs;
    }

    public void setCwlcs(List<CheckWeeklyListComment> cwlcs) {
        this.cwlcs = cwlcs;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }
}
