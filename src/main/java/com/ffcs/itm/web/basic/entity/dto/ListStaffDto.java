package com.ffcs.itm.web.basic.entity.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用于员工列表
 * @author sa
 *
 */
public class ListStaffDto {
	private Long staffId;
	
	private String staffName;
	
	private String staffType;
	
	private String userName;
	
	private String bprLineName;
	
	private String parentName;
	
	private String postName;
	
	private String regionName;
	
	private String state;
	
	private String orgId;
	
	private String orgName;
	
	private String stateName;
	
	private String orgIds;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getBprLineName() {
		return bprLineName;
	}

	public void setBprLineName(String bprLineName) {
		this.bprLineName = bprLineName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
