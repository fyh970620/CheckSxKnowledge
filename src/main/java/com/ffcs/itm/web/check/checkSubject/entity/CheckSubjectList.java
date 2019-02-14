package com.ffcs.itm.web.check.checkSubject.entity;

import java.io.Serializable;
import java.util.List;

public class CheckSubjectList implements Serializable {

    private Integer subjectId;  // ID
    private String subjectName; //技能名称
    private String subjectRemark; //技能描述
    private String subjectType; //技能类型
    private String subjectDest; //学习目标
    private Integer subjectTime; //学习天数
    private String subjectGroupType; //技能归属组分类1前台2后台3工程

    //附加属性(一对多)
    private List<CheckSubjectListAttach> csjla;
    private String knowTypeValue;
    private String knowGroupTypeValue;

    @Override
    public String toString() {
        return "CheckSubjectList{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectRemark='" + subjectRemark + '\'' +
                ", subjectType='" + subjectType + '\'' +
                ", subjectDest='" + subjectDest + '\'' +
                ", subjectTime=" + subjectTime +
                ", subjectGroupType='" + subjectGroupType + '\'' +
                ", csjla=" + csjla +
                ", knowTypeValue='" + knowTypeValue + '\'' +
                ", knowGroupTypeValue='" + knowGroupTypeValue + '\'' +
                '}';
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectRemark() {
        return subjectRemark;
    }

    public void setSubjectRemark(String subjectRemark) {
        this.subjectRemark = subjectRemark;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectDest() {
        return subjectDest;
    }

    public void setSubjectDest(String subjectDest) {
        this.subjectDest = subjectDest;
    }

    public Integer getSubjectTime() {
        return subjectTime;
    }

    public void setSubjectTime(Integer subjectTime) {
        this.subjectTime = subjectTime;
    }

    public String getSubjectGroupType() {
        return subjectGroupType;
    }

    public void setSubjectGroupType(String subjectGroupType) {
        this.subjectGroupType = subjectGroupType;
    }

    public List<CheckSubjectListAttach> getCsjla() {
        return csjla;
    }

    public void setCsjla(List<CheckSubjectListAttach> csjla) {
        this.csjla = csjla;
    }

    public String getKnowTypeValue() {
        return knowTypeValue;
    }

    public void setKnowTypeValue(String knowTypeValue) {
        this.knowTypeValue = knowTypeValue;
    }

    public String getKnowGroupTypeValue() {
        return knowGroupTypeValue;
    }

    public void setKnowGroupTypeValue(String knowGroupTypeValue) {
        this.knowGroupTypeValue = knowGroupTypeValue;
    }
}
