package com.ffcs.itm.web.check.checkStaffInfo.entity;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.entity.StaffUser;

import java.io.Serializable;
import java.util.Date;

public class CheckStaffInfo implements Serializable{

    private Integer staffId;       //'STAFF_ID'
    private String beginDate;        //'入司时间';
    private String school;          //'毕业学校';
    private String baseAddress;    //'籍贯';
    private String staffGroupType;//'1前台2后台3工程';
    private String staffTeacherName; //导师名称
    private String major;    //专业


    //附加属性
    private StaffUser staff;
    private String groupTypevalue;

    @Override
    public String toString() {
        return "CheckStaffInfo{" +
                "staffId=" + staffId +
                ", beginDate='" + beginDate + '\'' +
                ", school='" + school + '\'' +
                ", baseAddress='" + baseAddress + '\'' +
                ", staffGroupType='" + staffGroupType + '\'' +
                ", staffTeacherName='" + staffTeacherName + '\'' +
                ", major='" + major + '\'' +
                ", staff=" + staff +
                ", groupTypevalue='" + groupTypevalue + '\'' +
                '}';
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getStaffGroupType() {
        return staffGroupType;
    }

    public void setStaffGroupType(String staffGroupType) {
        this.staffGroupType = staffGroupType;
    }

    public String getStaffTeacherName() {
        return staffTeacherName;
    }

    public void setStaffTeacherName(String staffTeacherName) {
        this.staffTeacherName = staffTeacherName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public String getGroupTypevalue() {
        return groupTypevalue;
    }

    public void setGroupTypevalue(String groupTypevalue) {
        this.groupTypevalue = groupTypevalue;
    }
}
