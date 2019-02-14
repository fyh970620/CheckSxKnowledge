package com.ffcs.itm.web.check.checkStaffComment.entity;

import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;

public class CheckStaffComment implements Serializable{
    private Integer checkId;            //'COLLECT_ID';
    private Integer checkStaffId;       //'被考核人';
    private String visitCreateDate;       //'考核时间'
    private Integer staffId;            //'考核人';
    private String collectRemark;       //'评价内容';
    private Integer grade;              //'评价分数';
    private String staffGroupType;      //'1前台2后台3工程';

    //附加属性
    private StaffUser staffCheck;   //考核人
    private StaffUser staffChecked; //被考核人
    private String groupTypeValue;

    @Override
    public String toString() {
        return "CheckStaffComment{" +
                "checkId=" + checkId +
                ", checkStaffId=" + checkStaffId +
                ", visitCreateDate='" + visitCreateDate + '\'' +
                ", staffId=" + staffId +
                ", collectRemark='" + collectRemark + '\'' +
                ", grade=" + grade +
                ", staffGroupType='" + staffGroupType + '\'' +
                ", staffCheck=" + staffCheck +
                ", staffChecked=" + staffChecked +
                ", groupTypeValue='" + groupTypeValue + '\'' +
                '}';
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getCheckStaffId() {
        return checkStaffId;
    }

    public void setCheckStaffId(Integer checkStaffId) {
        this.checkStaffId = checkStaffId;
    }

    public String getVisitCreateDate() {
        return visitCreateDate;
    }

    public void setVisitCreateDate(String visitCreateDate) {
        this.visitCreateDate = visitCreateDate;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getCollectRemark() {
        return collectRemark;
    }

    public void setCollectRemark(String collectRemark) {
        this.collectRemark = collectRemark;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getStaffGroupType() {
        return staffGroupType;
    }

    public void setStaffGroupType(String staffGroupType) {
        this.staffGroupType = staffGroupType;
    }

    public StaffUser getStaffCheck() {
        return staffCheck;
    }

    public void setStaffCheck(StaffUser staffCheck) {
        this.staffCheck = staffCheck;
    }

    public StaffUser getStaffChecked() {
        return staffChecked;
    }

    public void setStaffChecked(StaffUser staffChecked) {
        this.staffChecked = staffChecked;
    }

    public String getGroupTypeValue() {
        return groupTypeValue;
    }

    public void setGroupTypeValue(String groupTypeValue) {
        this.groupTypeValue = groupTypeValue;
    }
}
