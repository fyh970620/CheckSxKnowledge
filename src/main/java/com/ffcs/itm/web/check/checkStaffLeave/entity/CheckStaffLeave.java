package com.ffcs.itm.web.check.checkStaffLeave.entity;

import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;

public class CheckStaffLeave implements Serializable {

    private Integer leaveId;
    private Integer checkStaffId;
    private String beginLeaveDate;
    private String endLeaveDate;
    private Double leaveDay;
    private String staffGroupType;

    //附加属性
    private StaffUser staff;
    private String groupTypeValue;

    @Override
    public String toString() {
        return "CheckStaffLeave{" +
                "leaveId=" + leaveId +
                ", checkStaffId=" + checkStaffId +
                ", beginLeaveDate='" + beginLeaveDate + '\'' +
                ", endLeaveDate='" + endLeaveDate + '\'' +
                ", leaveDay=" + leaveDay +
                ", staffGroupType='" + staffGroupType + '\'' +
                ", staff=" + staff +
                ", groupTypeValue='" + groupTypeValue + '\'' +
                '}';
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getCheckStaffId() {
        return checkStaffId;
    }

    public void setCheckStaffId(Integer checkStaffId) {
        this.checkStaffId = checkStaffId;
    }

    public String getBeginLeaveDate() {
        return beginLeaveDate;
    }

    public void setBeginLeaveDate(String beginLeaveDate) {
        this.beginLeaveDate = beginLeaveDate;
    }

    public String getEndLeaveDate() {
        return endLeaveDate;
    }

    public void setEndLeaveDate(String endLeaveDate) {
        this.endLeaveDate = endLeaveDate;
    }

    public Double getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(Double leaveDay) {
        this.leaveDay = leaveDay;
    }

    public String getStaffGroupType() {
        return staffGroupType;
    }

    public void setStaffGroupType(String staffGroupType) {
        this.staffGroupType = staffGroupType;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public String getGroupTypeValue() {
        return groupTypeValue;
    }

    public void setGroupTypeValue(String groupTypeValue) {
        this.groupTypeValue = groupTypeValue;
    }
}
