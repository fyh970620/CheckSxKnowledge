package com.ffcs.itm.web.check.checkKnow.entity;

import java.io.Serializable;

public class CheckKnowListAttach implements Serializable {

    private Integer attachId; //ID
    private String attachName;//附件名称
    private String attachRemark; //附件所在服务器路径
    private Integer knowId;  //技能名称id

    @Override
    public String toString() {
        return "CheckKnowListAttach{" +
                "attachId=" + attachId +
                ", attachName='" + attachName + '\'' +
                ", attachRemark='" + attachRemark + '\'' +
                ", knowId=" + knowId +
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

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }
}
