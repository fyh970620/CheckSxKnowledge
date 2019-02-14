package com.ffcs.itm.web.sso.support;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 双向单点签名加密、解密工具
 * @author sa
 *
 */
public class SSOTicketUtil {
	private static int REPREATE_NUM = 3;
	private static int RANDOM_STR_NUM = 2;
	
	/**
	 * 创建包含 val 的加密签名
	 * @param val
	 * @param key 由 AesUtil.generateKeyInString() 生成的 key
	 * @return
	 */
	public static String createTicket(String val, String key) {
		String result = "";
		for (int i = 0; i < REPREATE_NUM; i++) {
			result += val + getRandomStr();
		}
		
		return AesUtil.encrypt(result, key);
	}
	
	public static String createSimpleTicket(String val, String key) {
		String result = val + getRandomStr();
		return  AesUtil.encrypt(result, key);
	}
	
	private static String getRandomStr() {
		return RandomStringUtils.random(RANDOM_STR_NUM, true, true);
	}
	
	/**
	 * 从签名中提取 val
	 */
	public static String parseTicket(String ticket, String key) {
		String result = AesUtil.decrypt(ticket, key);
		int end = result.length() / REPREATE_NUM - RANDOM_STR_NUM;

		return result.substring(0, end);
	}
	
	public static String parseSimpleTicket(String ticket, String key) {
		String result = AesUtil.decrypt(ticket, key);
		int end = result.length() - RANDOM_STR_NUM;

		return result.substring(0, end);
	}
	
	public static void main(String[] args) {
		String[] userNames = new String[] {
			"linsha", "admin", "admin", "test", "longggggggname", "12345678901234567890123456789012345678901234567890"
		}; 
		
		for (String userName : userNames) {
			String key = AesUtil.generateStrKey();
			String ticket = SSOTicketUtil.createTicket(userName, key);
			System.out.println(ticket);
		}
		
		String userName = "admin";
		String ticketKey = "BFA0E8B6CEB1C2065AC1D955C3BC85DB";
		String ticketTimeKey = "D73E47A8830D09A11829B4B9510C3A2D";
		
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println(SSOTicketUtil.createTicket(userName, ticketKey));
		System.out.println(SSOTicketUtil.createSimpleTicket(userName + System.currentTimeMillis(), ticketTimeKey));
		System.out.println("-----------------------------------------------------------------------------------");
	}
}
