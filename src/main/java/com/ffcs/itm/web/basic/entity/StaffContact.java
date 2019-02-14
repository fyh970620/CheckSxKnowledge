package com.ffcs.itm.web.basic.entity;

import java.io.Serializable;

public class StaffContact implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7805401900499326341L;

	private Long staffId;

    private String linkmanName;

    private String mobile;

    private String phs;

    private String tel;

    private String address;

    private String fax;

    private String email;

    private String remark;

    private String voice;

    private String smsReceive;

    private String issend;

    private String smsGateway;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhs() {
        return phs;
    }

    public void setPhs(String phs) {
        this.phs = phs;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getSmsReceive() {
        return smsReceive;
    }

    public void setSmsReceive(String smsReceive) {
        this.smsReceive = smsReceive;
    }

    public String getIssend() {
        return issend;
    }

    public void setIssend(String issend) {
        this.issend = issend;
    }

    public String getSmsGateway() {
        return smsGateway;
    }

    public void setSmsGateway(String smsGateway) {
        this.smsGateway = smsGateway;
    }
}