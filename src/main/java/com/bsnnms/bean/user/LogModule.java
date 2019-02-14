package com.bsnnms.bean.user;

import java.io.Serializable;

public class LogModule implements Serializable{
	
	private int moduleId;
	
	private String moduleName;
	
	private int parentModuleId;
	
	private String moduleCode;
	
	private String comments;
	
	private String pageUrl;

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(int parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
}
