package com.ffcs.itm.web.check.checkKnow.entity;

import java.io.Serializable;
import java.util.List;

public class CheckKonwList implements Serializable{

    private Integer knowId;  // ID
    private String knowName; //技能名称
    private String knowRemark; //技能描述
    private String knowType; //技能类型
    private String knowDest; //学习目标
    private Integer knowTime; //学习天数
    private String knowGroupType; //技能归属组分类1前台2后台3工程

    //附加属性(一对多)
    private List<CheckKnowListAttach> cklas;
    private String knowTypeValue;
    private String knowGroupTypeValue;

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

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public String getKnowName() {
        return knowName;
    }

    public void setKnowName(String knowName) {
        this.knowName = knowName;
    }

    public String getKnowRemark() {
        return knowRemark;
    }

    public void setKnowRemark(String knowRemark) {
        this.knowRemark = knowRemark;
    }

    public String getKnowType() {
        return knowType;
    }

    public void setKnowType(String knowType) {
        this.knowType = knowType;
    }

    public String getKnowDest() {
        return knowDest;
    }

    public void setKnowDest(String knowDest) {
        this.knowDest = knowDest;
    }

    public Integer getKnowTime() {
        return knowTime;
    }

    public void setKnowTime(Integer knowTime) {
        this.knowTime = knowTime;
    }

    public String getKnowGroupType() {
        return knowGroupType;
    }

    public void setKnowGroupType(String knowGroupType) {
        this.knowGroupType = knowGroupType;
    }

    public List<CheckKnowListAttach> getCklas() {
        return cklas;
    }

    public void setCklas(List<CheckKnowListAttach> cklas) {
        this.cklas = cklas;
    }

    @Override
    public String toString() {
        return "CheckKonwList{" +
                "knowId=" + knowId +
                ", knowName='" + knowName + '\'' +
                ", knowRemark='" + knowRemark + '\'' +
                ", knowType='" + knowType + '\'' +
                ", knowDest='" + knowDest + '\'' +
                ", knowTime=" + knowTime +
                ", knowGroupType='" + knowGroupType + '\'' +
                ", cklas=" + cklas +
                ", knowTypeValue='" + knowTypeValue + '\'' +
                ", knowGroupTypeValue='" + knowGroupTypeValue + '\'' +
                '}';
    }
}
