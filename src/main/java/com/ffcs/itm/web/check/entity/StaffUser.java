package com.ffcs.itm.web.check.entity;

import java.io.Serializable;

public class StaffUser implements Serializable{

    private Integer staffId;
    private String staffname;

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", staffname='" + staffname + '\'' +
                '}';
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }
}
