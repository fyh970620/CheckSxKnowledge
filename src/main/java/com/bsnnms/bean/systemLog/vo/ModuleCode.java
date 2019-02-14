package com.bsnnms.bean.systemLog.vo;

/**
 * 模块编码定义，应与 TP_MODULE 表一致
 * @author sa
 *
 */
public interface ModuleCode {
	/**
	 * 用户管理
	 */
	static final int STAFF = 19010;
	
	static final int MAINT_JOB = 13030;
	
	/**
	 * 代理采集 
	 */
	static final int AGENT_GATHER = 21001;
	/**
	 * 稽核报告 
	 */
	static final int AUDIT_REPORT = 12020;	
	/**
	 * 规则配置 
	 */
	static final int RULE_CONFIG = 12080;
}
