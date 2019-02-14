package com.ffcs.itm.web.basic.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ffcs.itm.web.common.utils.MD5Encode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sa
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties({ "passwd", "handler", "encodePasswd" })
public class Staff implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3676306905297890750L;

	/**
	 * 超级管理员 id
	 */
	public static int ADMIN_ID = 1;

	private Long id;
    
    private String passwd;

    private String userName;
    
    private String staffName;
    
    private String pwdExpDate;
    
    private String scopeLevel;
    
    private String parentId;
    
    private String parentName;
    
    private String staffbId;
    
    private String staffbName;
    
    private String staffcId;
    
    private String staffcName;
    
    private String staffDesc;
    
    private String staffPost;
    
    private String staffLevel;
    
    private int regionId;
    
    private Organization organization;
    
    private String state;
    
    private String loginId;
    
    private String censorMsg;
    
    private Date stateDate;
    
    private int censorStaff;
    
    private Date censorDate;
    
    private String isOuterNetUser;
    
    private String delFlag;
    
    private String priLevel;
    
	private String accCertId;
	
	private String staffType;
	
	private String calledName;
	
	private Individual individual;
	
	private StaffContact staffContact;
	
	private int sortId;

	private String loginIp;

	private String regionLevel;

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getEncodePasswd() {
		return MD5Encode.encode(this.id + this.passwd);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPwdExpDate() {
		return pwdExpDate;
	}

	public void setPwdExpDate(String pwdExpDate) {
		this.pwdExpDate = pwdExpDate;
	}

	public String getScopeLevel() {
		return scopeLevel;
	}

	public void setScopeLevel(String scopeLevel) {
		this.scopeLevel = scopeLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getStaffbId() {
		return staffbId;
	}

	public void setStaffbId(String staffbId) {
		this.staffbId = staffbId;
	}

	public String getStaffbName() {
		return staffbName;
	}

	public void setStaffbName(String staffbName) {
		this.staffbName = staffbName;
	}

	public String getStaffcId() {
		return staffcId;
	}

	public void setStaffcId(String staffcId) {
		this.staffcId = staffcId;
	}

	public String getStaffcName() {
		return staffcName;
	}

	public void setStaffcName(String staffcName) {
		this.staffcName = staffcName;
	}

	public String getStaffDesc() {
		return staffDesc;
	}

	public void setStaffDesc(String staffDesc) {
		this.staffDesc = staffDesc;
	}

	public String getStaffPost() {
		return staffPost;
	}

	public void setStaffPost(String staffPost) {
		this.staffPost = staffPost;
	}

	public String getStaffLevel() {
		return staffLevel;
	}

	public void setStaffLevel(String staffLevel) {
		this.staffLevel = staffLevel;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getCensorMsg() {
		return censorMsg;
	}

	public void setCensorMsg(String censorMsg) {
		this.censorMsg = censorMsg;
	}

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public int getCensorStaff() {
		return censorStaff;
	}

	public void setCensorStaff(int censorStaff) {
		this.censorStaff = censorStaff;
	}

	public Date getCensorDate() {
		return censorDate;
	}

	public void setCensorDate(Date censorDate) {
		this.censorDate = censorDate;
	}

	public String getIsOuterNetUser() {
		return isOuterNetUser;
	}

	public void setIsOuterNetUser(String isOuterNetUser) {
		this.isOuterNetUser = isOuterNetUser;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getPriLevel() {
		return priLevel;
	}

	public void setPriLevel(String priLevel) {
		this.priLevel = priLevel;
	}

	public String getAccCertId() {
		return accCertId;
	}

	public void setAccCertId(String accCertId) {
		this.accCertId = accCertId;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getCalledName() {
		return calledName;
	}

	public void setCalledName(String calledName) {
		this.calledName = calledName;
	}

	public Individual getIndividual() {
		return individual;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	public StaffContact getStaffContact() {
		return staffContact;
	}

	public void setStaffContact(StaffContact staffContact) {
		this.staffContact = staffContact;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean isAdmin() {
		return ADMIN_ID == id;
	}

	public String getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
	}
}