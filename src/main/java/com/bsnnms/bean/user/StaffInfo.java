/**************************************************
 * Copyright (c) 2005.
 * 文件名称: StaffInfo.java
 * 摘　　要: 存储用户的登录信息,存放在Session中
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-3
 **************************************************/
package com.bsnnms.bean.user;

import java.io.Serializable;

/**
 * <p>
 * 员工实体类
 * </p>
 * 存储用户的登录信息,存放在Session中
 * 
 * @author 方旭尘
 * @version 1.0
 */
public class StaffInfo implements Serializable
{
    private int     staffId;

    private String  LoginName;

    private String  staffName;

    private String  loginId;

    private String  loginIp;

    private String  pswd;

    private int     regionId;

    private int     orgId;

    private boolean isKill;

    private String  state;

    private int     staffType;

    private String memberId;
    
    private String parent_staff_id;
    
    private String parent_staff_name;
    
    private String outerNetUser;
    
    private String mobile;
    
    private String email;
    
    private String priLevel;

    private String staffLocale;
    
    private String accCertId;

    public String getStaffLocale() {
        return staffLocale;
    }

    public void setStaffLocale(String staffLocale) {
        this.staffLocale = staffLocale;
    }
    
    public String getPriLevel() {
		return priLevel;
	}

	public void setPriLevel(String priLevel) {
		this.priLevel = priLevel;
	}

	/*用户所属虚拟团队ID*/
    private String groupId;
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }    
    
    public int getStaffType()
    {
        return staffType;
    }

    public void setStaffType(int staffType)
    {
        this.staffType = staffType;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * 设置员工ID
     * 
     * @param id
     *            员工ID
     */
    public void setStaffId(int id)
    {
        this.staffId = id;
    }

    /**
     * 设置员工名称
     * 
     * @param name
     *            员工名称
     */
    public void setStaffName(String name)
    {
        this.staffName = name;
    }

    /**
     * 设置员工登录ID
     * 
     * @param id
     *            员工登录ID
     */
    public void setLoginId(String id)
    {
        this.loginId = id;
    }

    /**
     * 设置员工登录IP
     * 
     * @param ip
     *            员工登录IP
     */
    public void setLoginIp(String ip)
    {
        this.loginIp = ip;
    }

    /**
     * 设置该员工是否杀掉进程
     * 
     * @param is
     */
    public void setIsKill(boolean is)
    {
        this.isKill = is;
    }

    /**
     * 得到员工ID
     * 
     * @return 员工ID
     */
    public int getStaffId()
    {
        return this.staffId;
    }

    /**
     * 得到员工名称
     * 
     * @return 员工名称
     */
    public String getStaffName()
    {
        return this.staffName;
    }

    /**
     * 得到员工登录ID
     * 
     * @return 员工登录ID
     */
    public String getLoginId()
    {
        return this.loginId;
    }

    /**
     * 得到员工登录IP
     * 
     * @return 员工登录IP
     */
    public String getLoginIp()
    {
        return this.loginIp;
    }

    /**
     * 得到员工当前进程是否被杀
     * 
     * @return 员工当前进程是否被杀
     */
    public boolean getIsKill()
    {
        return this.isKill;
    }

    /**
     * 得到员工所属区域
     * 
     * @return
     */
    public int getRegionId()
    {
        return regionId;
    }

    /**
     * 设置员工所属区域
     * 
     * @param regionId
     */
    public void setRegionId(int regionId)
    {
        this.regionId = regionId;
    }

    public String getLoginName()
    {
        return LoginName;
    }

    public void setLoginName(String loginName)
    {
        LoginName = loginName;
    }

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }

    public int getOrgId()
    {
        return orgId;
    }

    public void setOrgId(int orgId)
    {
        this.orgId = orgId;
    }
    
    public String getMemberId()
    {
        return memberId;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }

	public String getParent_staff_id() {
		return parent_staff_id;
	}

	public void setParent_staff_id(String parent_staff_id) {
		this.parent_staff_id = parent_staff_id;
	}

	public String getParent_staff_name() {
		return parent_staff_name;
	}

	public void setParent_staff_name(String parent_staff_name) {
		this.parent_staff_name = parent_staff_name;
	}
	
	public String getOuterNetUser() {
		return this.outerNetUser;
	}
	
	public void setOuterNetUser(String outerNetUser) {
		this.outerNetUser = outerNetUser;
	}
	
	public boolean isOuterNetUser() {
		if (this.outerNetUser != null 
				&& "0BT".equalsIgnoreCase(this.outerNetUser)) {
			return true;
		}
		
		return false;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAccCertId(){
		return accCertId;
	}
	
	public void setAccCertId(String accCertId) {
		this.accCertId = accCertId;
	}
}
