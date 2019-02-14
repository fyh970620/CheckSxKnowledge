/**************************************************
 * Copyright (c) 2005.
 * 文件名称: MD5Encode.java
 * 摘　　要: 对字符进行MD5加密
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-2
 **************************************************/
package com.ffcs.itm.common.utils;

import com.bsnnms.bean.common.StringUtils;
import com.bsnnms.exception.SystemException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * MD5加密
 * </p>
 * 对字符进行MD5加密
 * 
 * @author 方旭尘
 * @version 1.0
 */
public class MD5Encode
{
    private static MessageDigest alg = null;

    /**
     * 对指定的字符串进行MD5加密
     * @param str 需要加密的字符串
     * @return MD5加密后字符串
     * @throws SystemException
     */
    public static String encode(String str) throws SystemException
    {
        String encodeStr = "";
        try
        {
            if (alg == null)
            {
                alg = MessageDigest.getInstance("MD5");
            }
            alg.update(str.getBytes());
            encodeStr = StringUtils.byte2hex(alg.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new SystemException("380007", e);
        }
        return encodeStr;
    }
}
