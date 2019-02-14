package com.ffcs.itm.web.basic.entity;

import java.util.Date;

public class StaffPrivilege {
    private Integer staffId;

    private Integer privilegeId;

    private Integer impowerStaffId;

    private String privilegeEffUnit;

    private Integer offsetDays;

    private Integer effectDays;

    private Integer offsetDayTime;

    private Integer effectDayTime;

    private Date effDate;

    private Date expDate;

    private Short checked;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getImpowerStaffId() {
        return impowerStaffId;
    }

    public void setImpowerStaffId(Integer impowerStaffId) {
        this.impowerStaffId = impowerStaffId;
    }

    public String getPrivilegeEffUnit() {
        return privilegeEffUnit;
    }

    public void setPrivilegeEffUnit(String privilegeEffUnit) {
        this.privilegeEffUnit = privilegeEffUnit;
    }

    public Integer getOffsetDays() {
        return offsetDays;
    }

    public void setOffsetDays(Integer offsetDays) {
        this.offsetDays = offsetDays;
    }

    public Integer getEffectDays() {
        return effectDays;
    }

    public void setEffectDays(Integer effectDays) {
        this.effectDays = effectDays;
    }

    public Integer getOffsetDayTime() {
        return offsetDayTime;
    }

    public void setOffsetDayTime(Integer offsetDayTime) {
        this.offsetDayTime = offsetDayTime;
    }

    public Integer getEffectDayTime() {
        return effectDayTime;
    }

    public void setEffectDayTime(Integer effectDayTime) {
        this.effectDayTime = effectDayTime;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Short getChecked() {
        return checked;
    }

    public void setChecked(Short checked) {
        this.checked = checked;
    }
}