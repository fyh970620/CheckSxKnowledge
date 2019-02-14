package com.ffcs.itm.web.sso.entity;

import java.util.Date;

public class ItmSsoKey {
	public static final String TICKET_KEY_NAME = "ITM_SSO_TICKET_KEY";
	public static final String TICKET_TIME_KEY_NAME = "ITM_SSO_TICKET_TIME_KEY";
	
	private String ticketKey;
	
	private String ticketTimeKey;
	
	private Date updateTime;

	public String getTicketKey() {
		return ticketKey;
	}

	public void setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
	}

	public String getTicketTimeKey() {
		return ticketTimeKey;
	}

	public void setTicketTimeKey(String ticketTimeKey) {
		this.ticketTimeKey = ticketTimeKey;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
