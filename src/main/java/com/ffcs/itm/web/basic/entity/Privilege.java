package com.ffcs.itm.web.basic.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Privilege {
    private Long id;

    private Long parentId;

    private String name;

    private String comments;

    private String frontType;

    private String scriptName;

    private String ocxName;

    private Integer menuLevel;

    private String paramStr;

    private String serverUrlName;

    private String helpLinkName;

    private String path;

    private String activexId;

    private String version;

    private String state;

    private Date stateDate;

    private Integer sortId;

    private String isAddQuery;

    private String isEdit;

    private String funcDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFrontType() {
        return frontType;
    }

    public void setFrontType(String frontType) {
        this.frontType = frontType;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getOcxName() {
        return ocxName;
    }

    public void setOcxName(String ocxName) {
        this.ocxName = ocxName;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public String getServerUrlName() {
        return serverUrlName;
    }

    public void setServerUrlName(String serverUrlName) {
        this.serverUrlName = serverUrlName;
    }

    public String getHelpLinkName() {
        return helpLinkName;
    }

    public void setHelpLinkName(String helpLinkName) {
        this.helpLinkName = helpLinkName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getActivexId() {
        return activexId;
    }

    public void setActivexId(String activexId) {
        this.activexId = activexId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getIsAddQuery() {
        return isAddQuery;
    }

    public void setIsAddQuery(String isAddQuery) {
        this.isAddQuery = isAddQuery;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}