package com.ffcs.itm.web.sso.entity;

public class ItmSsoCfg {
	/**
	 * 单点系统的 url
	 */
	private String host;
	
	/**
	 * 中转的链接
	 */
	private String jumpLink;
	
	public ItmSsoCfg() {
		
	}
	
	public ItmSsoCfg(String host, String jumpLink) {
		this.host = host;
		this.jumpLink = jumpLink;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getJumpLink() {
		return jumpLink;
	}
	public void setJumpLink(String jumpLink) {
		this.jumpLink = jumpLink;
	}
	
	/**
	 * 中转的链接: 系统地址 + 中转页面
	 * @return
	 */
	public String getSwitchURL() {
		return this.host + this.jumpLink;
	}
}
