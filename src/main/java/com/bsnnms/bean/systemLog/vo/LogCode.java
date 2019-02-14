package com.bsnnms.bean.systemLog.vo;

/**
 * 系统日志的编码定义, 应与 LOG_CODE 表一致
 * @author sa
 *
 */
public interface LogCode {
	// 用户管理部分
	/**
	 * 用户登录
	 */
	static final String STAFF_LOGIN = "ITM51001";
	/**
	 * 用户登出
	 */
	static final String STAFF_LOGOUT = "ITM51002";
	
	/**
	 * 新增用户 
	 */
	static final String STAFF_ADD = "ITM51101";
	/**
	 * 修改用户
	 */
	static final String STAFF_EDIT = "ITM51102";
	/**
	 * 删除用户
	 */
	static final String STAFF_DEL = "ITM51103";
	
	/**
	 * 新增用户(接口)
	 */
	static final String STAFF_ADD_INTERFACE = "ITM51104";
	/**
	 * 修改用户(接口)
	 */
	static final String STAFF_EDIT_INTERFACE = "ITM51105";
	/**
	 * 删除用户(接口)
	 */
	static final String STAFF_DEL_INTERFACE = "ITM51106";
	
	/**
	 * 新增部门 
	 */
	static final String ORG_ADD = "ITM51201";
	/**
	 * 修改部门
	 */
	static final String ORG_EDIT = "ITM51202";
	/**
	 * 删除部门
	 */
	static final String ORG_DEL = "ITM51203";
	
	/**
	 * 新增部门 (接口)
	 */
	static final String ORG_ADD_INTERFACE = "ITM51204";
	/**
	 * 修改部门(接口)
	 */
	static final String ORG_EDIT_INTERFACE = "ITM51205";
	/**
	 * 删除部门(接口)
	 */
	static final String ORG_DEL_INTERFACE = "ITM51206";
	

	// 维护作业管理部分
	/**
	 * 新增作业模板
	 */
	static final String MAINT_JOB_MODEL_ADD = "ITM52001";
	/**
	 * 修改作业模板
	 */
	static final String MAINT_JOB_MODEL_EDIT = "ITM52002";
	/**
	 * 删除作业模板
	 */
	static final String MAINT_JOB_MODEL_DEL = "ITM52003";
	
	/**
	 * 新增项目模板
	 */
	static final String MAINT_JOB_ITEM_MODEL_ADD = "ITM52004";
	/**
	 * 修改项目模板
	 */
	static final String MAINT_JOB_ITEM_MODEL_EDIT = "ITM52005";
	/**
	 * 删除项目模板
	 */
	static final String MAINT_JOB_ITEM_MODEL_DEL = "ITM52006";
	
	/**
	 * 新增作业
	 */
	static final String MAINT_JOB_ADD = "ITM52101";
	/**
	 * 修改作业
	 */
	static final String MAINT_JOB_EDIT = "ITM52102";
	/**
	 * 删除作业
	 */
	static final String MAINT_JOB_DEL = "ITM52103";
	
	/**
	 * 新增项目
	 */
	static final String MAINT_JOB_ITEM_ADD = "ITM52104";
	/**
	 * 修改项目
	 */
	static final String MAINT_JOB_ITEM_EDIT = "ITM52105";
	/**
	 * 删除项目
	 */
	static final String MAINT_JOB_ITEM_DEL = "ITM52106";
	/**
	 * 修改代理采集
	 */
	static final String AGENT_GATHER_EDIT = "ITM52401";
	/**
	 * 修改稽核报告
	 */
	static final String AUDIT_REPORT_EDIT = "ITM52501";
	/**
	 * 修改告警规则
	 */
	static final String RULE_CONFIG_EDIT = "ITM52601";
}
