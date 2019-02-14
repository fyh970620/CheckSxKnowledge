package com.ffcs.itm.web.check.weeklyList.entity;


import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;

public class GoodWeekly implements Serializable{

    private Integer topId;                //'TOP_ID';
    private Integer topStaffId;           //'推荐人';
    private Date  visitCreateDate;        //'推荐时间';
    private Integer weeklyId;             //'周报ID';

    private String time;

    //附加属性
    private StaffUser staff;
    private CheckWeeklyList cwl;

    @Override
    public String toString() {
        return "GoodWeekly{" +
                "topId=" + topId +
                ", topStaffId=" + topStaffId +
                ", visitCreateDate=" + visitCreateDate +
                ", weeklyId=" + weeklyId +
                ", time='" + time + '\'' +
                ", staff=" + staff +
                ", cwl=" + cwl +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public CheckWeeklyList getCwl() {
        return cwl;
    }

    public void setCwl(CheckWeeklyList cwl) {
        this.cwl = cwl;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getTopStaffId() {
        return topStaffId;
    }

    public void setTopStaffId(Integer topStaffId) {
        this.topStaffId = topStaffId;
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
}
