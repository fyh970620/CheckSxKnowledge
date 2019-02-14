package com.ffcs.itm.web.sso.support;

import com.bsnnms.bean.common.AESSecurity;
import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.common.utils.date.DateUtil;
import com.ffcs.itm.web.sso.entity.ItmSsoCfg;
import com.ffcs.itm.web.sso.entity.ItmSsoKey;
import com.ffcs.itm.web.sso.entity.TicketInfo;
import com.ffcs.itm.web.sso.service.TicketService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

@Component
public class TicketCenter {
	private static Logger LOGGER = LoggerFactory.getLogger(TicketCenter.class);
	
	private static final String ENCODE_UTF_8 = "UTF-8";
	
	public static final String TICKET_NAME = "itmticket";
	public static final String TICKET_TIME_NAME = "r";
	
	public static final String PARAM_TARGET_URL = "targetURL";
	
	/**
	 * 是否启用 ticketCenter
	 */
	private static boolean ACTIVE = false;
	
	/**
	 * ticket 失效时间（单位微秒）: 5 分钟
	 */
	public static final long TICKET_EXPIRED_TIME = 5 * 60 * 1000;
	
	private static ItmSsoKey SSO_KEY_HOLDER;
	
	private static ItmSsoCfg SSO_CFG;
	
	private static TicketService TICKET_SERVICE;
		
	@Autowired(required = true)
	public void setUserAccessor(TicketService ticketService) {
		TicketCenter.TICKET_SERVICE = ticketService;
	}
	
	@PostConstruct
	public void init() {
		setItmSsoKey(TICKET_SERVICE.getSsoKey());
		SSO_CFG = TICKET_SERVICE.getSsoCfg();
		ACTIVE = true;
		
		LOGGER.info("----TicketCenter has inited--");
	}
	
	public static boolean hasActive() {
	    return ACTIVE;
	}
	
    public static void createTicket(HttpSession session) {
        createTicket(session, TICKET_SERVICE.getUserName(session));
    }
    
	public static void createTicket(HttpSession session, String userName) {
	    if (StringUtils.isBlank(userName)) {
	        LOGGER.error("createTicket(): userName is empty or null");
	        return;
	    }
	    
	    if (SSO_KEY_HOLDER == null) {
	    	LOGGER.error("ssoKey 未正确初始，请检查 ITM_SSO_KEY 表是否有记录");
	        return;
	    }
	    
		String ticket = SSOTicketUtil.createTicket(userName, SSO_KEY_HOLDER.getTicketKey());
		setTicket(session, ticket);
	}

    public static void removeTicket(HttpSession session) {
        session.removeAttribute(TICKET_NAME);
    }
	
	public static void setTicket(HttpSession session, String ticket) {
		session.setAttribute(TICKET_NAME, ticket);
	}
	
	public static void setItmSsoKey(ItmSsoKey ssoKey) {
		SSO_KEY_HOLDER = ssoKey;
	}
	
	public static ItmSsoKey getItmSsoKey() {
		return SSO_KEY_HOLDER;
	}

	public static void setItmSsoCfg(ItmSsoCfg ssoCfg) {
		TicketCenter.SSO_CFG = ssoCfg;
	}

	public static ItmSsoCfg getItmSsoCfg() {
		return SSO_CFG;
	}
	
	/**
	 * 是否为当天生成的 key
	 */
	public static boolean keyIsCurrentDate() {
		return DateUtils.isSameDay(SSO_KEY_HOLDER.getUpdateTime(), new Date());
	}
	
	public static String getTicketKey() {
		return SSO_KEY_HOLDER.getTicketKey();
	}
	
	public static String getTicketTimeKey() {
		return SSO_KEY_HOLDER.getTicketTimeKey();
	}
	
	public static String getTicket(HttpSession session) {
		return (String)session.getAttribute(TICKET_NAME);
	}
	
	public static String getTicket(HttpServletRequest request) {
		return getTicket(request.getSession());
	}
	
	public static String getUserName(HttpServletRequest request) {
		return TICKET_SERVICE.getUserName(request.getSession());
	}
	
	/**
	 * 获取单点的中转链接
	 * @param request
	 * @param url
	 * @return
	 */
	public static Object getSSOURL(HttpServletRequest request, String url) {
		String result = SSO_CFG.getSwitchURL();
		
		try {
			result += "?" + PARAM_TARGET_URL + "=" + URLEncoder.encode(url, ENCODE_UTF_8);
		} catch (UnsupportedEncodingException e) {
		}
		
		result += "&" + TICKET_NAME + "=" + getTicket(request);
		result += "&" + TICKET_TIME_NAME + "=" + createTimeTicket(request);
		
		return result;
	}
	
	public static TicketInfo getTicketInfo(ServletRequest request) {
		TicketInfo result = new TicketInfo();
		
		String encodeTicket = request.getParameter(TICKET_NAME);
		String encodeTicketTime = request.getParameter(TICKET_TIME_NAME);

		LOGGER.info("--------------------debug-----------------------------");
		LOGGER.info("encodeTicket:" + request.getParameter(TICKET_NAME));
		LOGGER.info("encodeTicketTime:" + request.getParameter(TICKET_TIME_NAME));
		LOGGER.info("--------------------debug-----------------------------");
		
		if (StringUtils.isBlank(encodeTicket) || StringUtils.isBlank(encodeTicketTime)) {
			return result;
		}
		
		result.setTicket(encodeTicket);
		
		try {
			result.setUserName(SSOTicketUtil.parseTicket(encodeTicket, getTicketKey()));
			result.setTicketTime(SSOTicketUtil.parseSimpleTicket(encodeTicketTime, getTicketTimeKey()));			
		} catch(Exception e) {
			LOGGER.error("not valid ticket:[" + encodeTicket + "," + encodeTicketTime + "]", e);
		}

		return result;
	}
	
	private static String createTimeTicket(HttpServletRequest request) {
		 return SSOTicketUtil.createSimpleTicket(getUserName(request) + System.currentTimeMillis(), TicketCenter.getTicketTimeKey());
	}
	
	/**
	 * ticket 时间是否过期
	 * @param ticketTime
	 * @return
	 */
	public static boolean isExpired(String userName, String ticketTime) {
		if (StringUtils.isBlank(ticketTime)) {
			return true;
		}
		
		if (!ticketTime.startsWith(userName)) {
			return true;
		}
		
		try {
			long realTicketTime = Long.parseLong(ticketTime.substring(userName.length()));

			return (System.currentTimeMillis() - realTicketTime) > TICKET_EXPIRED_TIME;
		} catch (NumberFormatException e) {
			return true;
		}
	}

    /**
     * 单点跳转至目标 url
     * @param request
     * @param response
     * @throws IOException
     */
    public static void switchToTargetURL(HttpServletRequest request, HttpServletResponse response) {
        TicketInfo ticketInfo = TicketCenter.getTicketInfo(request);
        RouteTool routeTool = new RouteTool(request, response);
        
        if (!ticketInfo.isValid()) {
            routeTool.goToDefaultPage();
            return;
        }
        
        if (!ticketInfo.getUserName().equals(getUserName(request))) {
            TICKET_SERVICE.loginByTicket(request, ticketInfo);
        }
        
        routeTool.redirectTo(getTargetURL(request));
    }

	public static void restAccess(HttpServletRequest request, HttpServletResponse response) {
		TicketInfo ticketInfo = TicketCenter.getTicketInfo(request);

		if (!ticketInfo.isValid()) {
			//routeTool.goToDefaultPage();
			return;
		}

		if (!ticketInfo.getUserName().equals(getUserName(request))) {
			TICKET_SERVICE.loginByTicket(request, ticketInfo);
		}

		try {
			String url = request.getRequestURI().replaceFirst("/rest", "");

			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			LOGGER.error("url 转发异常", e);
		} catch (IOException e) {
			LOGGER.error("url 转发异常", e);
		}
	}

	public static void restOutSysAccess(HttpServletRequest request, HttpServletResponse response) {
		String authToken = request.getHeader("authToken");
		try {
			String keyTime = AESSecurity.decode(authToken, "FFCS_IOD");
			Date nowDate = new Date();
			Date keyDate = DateUtil.strToDateTime(keyTime);
			if (Math.abs((keyDate.getTime() - nowDate.getTime())) < TICKET_EXPIRED_TIME) {
				String url = request.getRequestURI().replaceFirst("/rest", "");

				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (ApplicationException e) {
			LOGGER.error("authToken解析异常或超时", e);
			e.printStackTrace();
		} catch (ServletException e) {
			LOGGER.error("url 转发异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("url 转发异常", e);
			e.printStackTrace();
		}

	}
    
    private static String getTargetURL(HttpServletRequest request) {
        String result = request.getParameter(PARAM_TARGET_URL);
        try {
            result = URLDecoder.decode(result, ENCODE_UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        
        return result;
    }

    //获取taken
    public static void main(String args[]) {
		try {
			System.out.println(AESSecurity.encode("2018-06-19 16:44:00","FFCS_IOD"));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
}
