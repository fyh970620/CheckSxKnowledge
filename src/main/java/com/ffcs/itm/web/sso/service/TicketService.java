package com.ffcs.itm.web.sso.service;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.service.StaffService;
import com.ffcs.itm.web.common.service.SysConfigService;
import com.ffcs.itm.web.sso.entity.ItmSsoCfg;
import com.ffcs.itm.web.sso.entity.ItmSsoKey;
import com.ffcs.itm.web.sso.entity.TicketInfo;
import com.ffcs.itm.web.sso.respository.TicketMapper;
import com.ffcs.itm.web.sso.support.TicketCenter;
import com.ffcs.itm.web.support.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class TicketService {
	@Autowired
	private TicketMapper ticketMapper;
	
	@Autowired
	private SysConfigService sysConfigService;

	@Autowired
	private StaffService staffService;
	
	private final static String SYS_VAR_SSO_CFG = "ITM_SSO_CFG";
	
	/**
	 * 获取单点配置
	 * @return
	 */
	public ItmSsoCfg getSsoCfg() {
		Map<String, Object> map = sysConfigService.getSysValueToMap(SYS_VAR_SSO_CFG);
		String host = map.get("OLD_SYS_HOST").toString();
		String jumpLink = map.get("OLD_SYS_JUMP_LINK").toString();
		
		return new ItmSsoCfg(host, jumpLink);
		
		//return ticketMapper.getSsoCfg();
	}

	public void updateSsoKey(ItmSsoKey ssoKey) {
		ticketMapper.updateSsoKey(ssoKey);
	}
	
	public ItmSsoKey getSsoKey() {
		return ticketMapper.getSsoKey(false);
	}
	
	public ItmSsoKey getSsoKey(boolean isCurrentDate) {
		return ticketMapper.getSsoKey(isCurrentDate);
	}
	
	public String getUserName(HttpSession session) {
		Staff staff = SessionInfo.getStaff(session);

		if (staff == null) {
			return null;
		}

		return staff.getUserName();
	}

	public void loginByTicket(HttpServletRequest request, TicketInfo ticketInfo) {
		String userName = ticketInfo.getUserName();
		
		Staff staff = staffService.findStaffByUserName(userName);

		HttpSession session = request.getSession();
        SessionInfo.setStaff(staff);

		TicketCenter.createTicket(session, userName);
		TicketCenter.setTicket(session, ticketInfo.getTicket());
	}
}