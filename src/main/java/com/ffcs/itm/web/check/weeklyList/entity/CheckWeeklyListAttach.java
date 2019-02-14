package com.ffcs.itm.web.check.weeklyList.entity;

import com.ffcs.itm.web.check.entity.CheckWeeklyList;

import java.io.Serializable;

public class CheckWeeklyListAttach implements Serializable{

    private Integer attachId;        //'主键ID'
    private String attachName;       //'附件名称'
    private String attachRemark;     //'附件所在服务器路径'
    private Integer weeklyId;        //'周报ID'

    @Override
    public String toString() {
        return "CheckWeeklyListAttach{" +
                "attachId=" + attachId +
                ", attachName='" + attachName + '\'' +
                ", attachRemark='" + attachRemark + '\'' +
                ", weeklyId=" + weeklyId +
                '}';
    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachRemark() {
        return attachRemark;
    }

    public void setAttachRemark(String attachRemark) {
        this.attachRemark = attachRemark;
    }

    public Integer getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }
}
