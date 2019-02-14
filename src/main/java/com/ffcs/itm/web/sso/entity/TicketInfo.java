package com.ffcs.itm.web.sso.entity;

import com.ffcs.itm.web.sso.support.TicketCenter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketInfo {
	Logger logger = LoggerFactory.getLogger(TicketInfo.class);

	private String ticket;
	
	private String ticketTime;
	
	private String userName;
	
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTicketTime() {
		return ticketTime;
	}

	public void setTicketTime(String ticketTime) {
		this.ticketTime = ticketTime;
	}

	public boolean isValid() {
		if (StringUtils.isBlank(userName) || TicketCenter.isExpired(userName, ticketTime)) {
			logger.error("ticket is inValid: StringUtils.isBlank(userName)" + StringUtils.isBlank(userName));
			logger.error("ticket is inValid: TicketCenter.isExpired(userName, ticketTime)" + TicketCenter
                    .isExpired(userName, ticketTime));
			return false;
		} else {
			return true;
		}
	}
}
