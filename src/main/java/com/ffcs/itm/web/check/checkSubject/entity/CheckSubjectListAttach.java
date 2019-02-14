package com.ffcs.itm.web.check.checkSubject.entity;

import java.io.Serializable;

public class CheckSubjectListAttach implements Serializable {

    private Integer attachId; //ID
    private String attachName;//附件名称
    private String attachRemark; //附件所在服务器路径
    private Integer subjectId;  //技能名称id

    @Override
    public String toString() {
        return "CheckSubjectListAttach{" +
                "attachId=" + attachId +
                ", attachName='" + attachName + '\'' +
                ", attachRemark='" + attachRemark + '\'' +
                ", subjectId=" + subjectId +
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
