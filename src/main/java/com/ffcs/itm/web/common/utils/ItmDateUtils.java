package com.ffcs.itm.web.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 *  日期时间工具简单包装类
 *  时间格式化，可直接调用 apache common 的 DateFormatUtils 工具
 *  时间比较、增减、解析，可直接调用 apache common 的 DateUtils 工具
 */
public class ItmDateUtils {
	private static Logger logger = LoggerFactory.getLogger(ItmDateUtils.class);
	
	public final static String SHORT_TIME_FORMAT = "yyyy-MM-dd";
	public final static String NORMAL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前系统时间
	 * @return 返回 yyyy-MM-dd HH:mm:ss 格式的的系统时间
	 */
	public static String getSysDate() {
		return getSySDate(NORMAL_TIME_FORMAT);
	}
	
	public static String getSySDate(String pattern) {
		return dateToStr(new Date(), pattern);
	}
	
	/**
	 * 日期转文本
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, SHORT_TIME_FORMAT);
	}
	
	public static String dateToStr(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		
		return DateFormatUtils.format(date, pattern);
	}
	
	/**
	 * 文本转日期
	 * @param dateStr
	 * @return
	 */
	public static Date strToDate(String dateStr) {
		return strToDate(dateStr, SHORT_TIME_FORMAT);
	}
	
	public static Date strToDate(String dateStr, String pattern) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		
		try {
			return DateUtils.parseDate(dateStr, pattern);
		} catch (ParseException e) {
			logger.warn("时间解析异常，dateStr:" + dateStr, e);
			return null;
		}
	}
}
