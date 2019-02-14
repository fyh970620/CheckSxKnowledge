package com.ffcs.itm.web.basic.entity;

import java.util.Date;

public class Role {
    /**
     * 网元标签管理员 id
     */
    public static final int TAG_MANAGER_ROLE_ID = 13;

    private Long id;
    
    /**
     * 角色名(等于 id,xx..)
     */
    private String name;
    
    private String nameCn;
    
    private String desc;
    
    /**
     * 角色类型（0=虚拟角色，1=模块角色）
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人 id
     */
    private Long createStaff;
    
    /**
     * 创建人名称
     */
    private String createStaffName;
    
    /**
     * 所属模块 
     */
    private String moduleName;
    
    /**
     * 是否系统固化
     */
    private boolean isSysFixed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCreateStaffName() {
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName) {
        this.createStaffName = createStaffName;
    }

	public boolean getIsSysFixed() {
		return isSysFixed;
	}

	public void setIsSysFixed(boolean isSysFixed) {
		this.isSysFixed = isSysFixed;
	}
}
