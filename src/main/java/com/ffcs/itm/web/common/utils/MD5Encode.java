package com.ffcs.itm.web.common.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>MD5 加密</p>
 * 
 * 对字符进行 MD5 加密
 * 
 */
public class MD5Encode {
	private static MessageDigest alg;

	/**
	 * 对指定的字符串进行 MD5 加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return MD5 加密后字符串
	 */
	public static String encode(String str) {
		try {
			if (alg == null) {
				alg = MessageDigest.getInstance("MD5");
			}
			
			alg.update(str.getBytes());
			
			return Hex.encodeHexString(alg.digest()).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// will not happen
		}
		
		return "";
	}
}
