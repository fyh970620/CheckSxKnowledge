package com.ffcs.itm.web.check.weeklyList.entity;

import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;

public class CheckWeeklyVisitList implements Serializable{
    private Integer visitId;
    private Integer visitStaffId;
    private Date visitCreateDate;
    private Integer weeklyId;
    private String time;

    //附加属性
    private StaffUser staffUser;

    @Override
    public String toString() {
        return "CheckWeeklyVisitList{" +
                "visitId=" + visitId +
                ", visitStaffId=" + visitStaffId +
                ", visitCreateDate=" + visitCreateDate +
                ", weeklyId=" + weeklyId +
                ", time='" + time + '\'' +
                ", staffUser=" + staffUser +
                '}';
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Integer getVisitStaffId() {
        return visitStaffId;
    }

    public void setVisitStaffId(Integer visitStaffId) {
        this.visitStaffId = visitStaffId;
    }

    public Date getVisitCreateDate() {
        return visitCreateDate;
    }

    public void setVisitCreateDate(Date visitCreateDate) {
        this.visitCreateDate = visitCreateDate;
    }

    public Integer getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StaffUser getStaffUser() {
        return staffUser;
    }

    public void setStaffUser(StaffUser staffUser) {
        this.staffUser = staffUser;
    }
}
