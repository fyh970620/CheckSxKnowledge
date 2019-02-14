package com.ffcs.itm.web.check.weeklyList.entity;


import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CheckWeeklyCollect implements Serializable{
    
    private Integer collectId;          //'COLLECT_ID'
    private Integer collectStaffId;     //'收藏人';
    private Date visitCreateDate;       //'收藏人时间';
    private Integer weeklyId;           //'周报ID'
    private String collectRemark;       //'收藏备注'
    private String time;
    //附加属性
    private StaffUser staff;
    private List<CheckWeeklyList> collectMoreWeeklys;   //收藏多个周报
    private CheckWeeklyList cwl;

    @Override
    public String toString() {
        return "CheckWeeklyCollect{" +
                "collectId=" + collectId +
                ", collectStaffId=" + collectStaffId +
                ", visitCreateDate=" + visitCreateDate +
                ", weeklyId=" + weeklyId +
                ", collectRemark='" + collectRemark + '\'' +
                ", time='" + time + '\'' +
                ", staff=" + staff +
                ", collectMoreWeeklys=" + collectMoreWeeklys +
                ", cwl=" + cwl +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CheckWeeklyList getCwl() {
        return cwl;
    }

    public void setCwl(CheckWeeklyList cwl) {
        this.cwl = cwl;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getCollectStaffId() {
        return collectStaffId;
    }

    public void setCollectStaffId(Integer collectStaffId) {
        this.collectStaffId = collectStaffId;
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

    public String getCollectRemark() {
        return collectRemark;
    }

    public void setCollectRemark(String collectRemark) {
        this.collectRemark = collectRemark;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public List<CheckWeeklyList> getCollectMoreWeeklys() {
        return collectMoreWeeklys;
    }

    public void setCollectMoreWeeklys(List<CheckWeeklyList> collectMoreWeeklys) {
        this.collectMoreWeeklys = collectMoreWeeklys;
    }
}
