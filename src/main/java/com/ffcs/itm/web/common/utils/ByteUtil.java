package com.ffcs.itm.web.common.utils;

import java.io.UnsupportedEncodingException;

public class ByteUtil {
    public static String byte2hex(byte[] src) {
        String tmp;
        int len = src.length;
        StringBuffer sb = new StringBuffer(len * 2);
        
        for (int i = 0; i < len; i++) {
            tmp = (Integer.toHexString(src[i] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
                sb.append(tmp);
            } else {
                sb.append(tmp);
            }
        }
        
        return (sb.toString()).toUpperCase();
    }

    public static byte[] hex2byte(String src) {
        return hex2byte(src.getBytes());
    }
    
    public static byte[] hex2byte(byte[] bytes) {
        int len = bytes.length;
        
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i = i + 2) {
            String strTmp = new String(bytes, i, 2);
            result[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        
        return result;
    }
    
	public static byte[] hex2byte(String src, String encodeType) throws UnsupportedEncodingException {
		byte[] bytes = src.getBytes(encodeType);
		int iLen = bytes.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(bytes, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
}
