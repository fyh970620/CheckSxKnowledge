package com.ffcs.itm.web.basic.support;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * 校验码存放工具
 * @author sa
 *
 */
public class ValidCodeHolder {
	private static final String SES_CODE_NAME = "___SES_VALID_CODE_NAME";
	private static final String SES_CODE_TIME = "___SES_VALID_CODE_TIME";

	/**
	 * 校验码默认存放有效时间 20 分钟
	 */
	private static final int DEFAULT_SESSION_LIMIT_TIME = 20;

	private HttpSession session;
	private String inputCode;

	public static ValidCodeHolder getInstance(HttpSession session) {
		return new ValidCodeHolder(session);
	}

	public ValidCodeHolder(HttpSession session) {
		this.session = session;
	}

	public void setCode(String code) {
		this.session.setAttribute(SES_CODE_NAME, code);
		this.session.setAttribute(SES_CODE_TIME, System.currentTimeMillis());
	}

	public String getCode() {
		return (String) this.session.getAttribute(SES_CODE_NAME);
	}

	/**
	 * 用户输入的验证码
	 * @param validCode
	 */
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	/**
	 * 验证码是否超时失效，失效返回 true
	 * @return
	 */
	public boolean isTimeout(String limitTime) {
		long s = (Long) this.session.getAttribute(SES_CODE_TIME);
		long e = System.currentTimeMillis();

		int limitInMiniute = 0;
		if (!StringUtils.isEmpty(limitTime)) {
			limitInMiniute = Integer.parseInt(limitTime);
		}
		if (limitInMiniute <= 0) {
			limitInMiniute = DEFAULT_SESSION_LIMIT_TIME;
		}
		return (e - s) > (limitInMiniute * 60 * 1000);
	}

	/**
	 * 用户输入的验证码是否错误， 错误返回 true
	 * @return
	 */
	public boolean isWrongCode() {
		String validCode = (String) this.session.getAttribute(SES_CODE_NAME);

		if (StringUtils.isEmpty(inputCode) || StringUtils.isEmpty(validCode) || !validCode.equalsIgnoreCase(inputCode)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 注销验证码
	 */
	public void destroy() {
		this.session.removeAttribute(SES_CODE_NAME);
		this.session.removeAttribute(SES_CODE_TIME);
	}
}
