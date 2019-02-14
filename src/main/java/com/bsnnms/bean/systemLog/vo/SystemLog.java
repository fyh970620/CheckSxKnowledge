package com.bsnnms.bean.systemLog.vo;

/**
 * 日志对象
 * @author sa
 *
 */
public class SystemLog {
	/**
	 * 模块 id
	 */
	private int moduleId;
	/**
	 * 日志编码
	 */
	private String logCode;
	/**
	 * 操作用户 id
	 */
	private int staffId;
	/**
	 * 日志内容
	 */
	private String content;
	/**
	 * 所操作对象id
	 */
	private String objId;
	/**
	 * 所操作对象名称
	 */
	private String objName;
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getLogCode() {
		return logCode;
	}
	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
}
