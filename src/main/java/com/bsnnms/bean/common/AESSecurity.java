package com.bsnnms.bean.common;

import com.bsnnms.exception.ApplicationException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class AESSecurity
{
    private static final int    KEY_BIT         = 128;

    private static final String TYPE            = "AES";

    private static final String DEFAULT_MODE    = "ECB";

    private static final String DEFAULT_PADDING = "PKCS5Padding";
    // private static final String DEFAULT_PADDING = "ZeroBytePadding";

    static
    {
        Security
                .addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private static final Key getKey(String key)
    {
        byte[] keys = key.getBytes();
        byte[] keyBTmp = new byte[KEY_BIT / 8];

        for (int i = 0; i < keys.length && i < keyBTmp.length; i++)
        {
            keyBTmp[i] = keys[i];
        }
        return new SecretKeySpec(keyBTmp, TYPE);
    }

    private static final String getSecurityType(String mode, String padding)
    {
        StringBuffer sbuf = new StringBuffer(TYPE);
        sbuf.append("/").append(mode);
        sbuf.append("/").append(padding);
        return sbuf.toString();
    }

    public static String encode(String str, String key, String mode,
            String padding) throws ApplicationException
    {
        Key secretKey = getKey(key);
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance(getSecurityType(mode, padding));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] b = cipher.doFinal(str.getBytes());
            return StringUtils.byte2hex(b);
        }
        catch (Exception e)
        {
            throw new ApplicationException("AES加密错误", e);
        }
    }

    public static String decode(String str, String key, String mode,
            String padding) throws ApplicationException
    {
        Key secretKey = getKey(key);
        Cipher cipher;
        try
        {
            byte[] b = StringUtils.hex2byte(str);
            cipher = Cipher.getInstance(getSecurityType(mode, padding));
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(b));
        }
        catch (Exception e)
        {
            throw new ApplicationException("AES解密错误", e);
        }
    }

    public static String encode(String str, String key)
            throws ApplicationException
    {
        return encode(str, key, DEFAULT_MODE, DEFAULT_PADDING);
    }

    public static String decode(String str, String key)
            throws ApplicationException
    {
        return decode(str, key, DEFAULT_MODE, DEFAULT_PADDING);
    }
    
    public static String decode(String str, String key, String mode,
            String padding,String encodeType) throws ApplicationException
    {
        Key secretKey = getKey(key);
        Cipher cipher;
        try
        {
            byte[] b = StringUtils.hex2byte(str,encodeType);
            cipher = Cipher.getInstance(getSecurityType(mode, padding));
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(b));
        }
        catch (Exception e)
        {
            throw new ApplicationException("AES解密错误", e);
        }
    }
}
