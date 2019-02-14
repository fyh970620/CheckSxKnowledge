package com.bsnnms.bean.common.security;

import java.util.Arrays;

public class BASE64Encoder
{

    public BASE64Encoder()
    {
    }

    private int convertUnsignedByteToInt(byte b)
    {
        if(b >= 0)
            return b;
        else
            return 256 + b;
    }

    public String encode(byte data[])
    {
        int charCount = (data.length * 4) / 3 + 4;
        StringBuffer result = new StringBuffer((charCount * 77) / 76);
        int byteArrayLength = data.length;
        int byteArrayIndex = 0;
        int byteTriplet = 0;
        while(byteArrayIndex < byteArrayLength - 2) 
        {
            byteTriplet = convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<= 8;
            byteTriplet |= convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<= 8;
            byteTriplet |= convertUnsignedByteToInt(data[byteArrayIndex++]);
            byte b4 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b3 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b2 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b1 = (byte)(0x3f & byteTriplet);
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            result.append(mapByteToChar(b3));
            result.append(mapByteToChar(b4));
            if(byteArrayIndex % 57 == 0)
                result.append("\n");
        }
        if(byteArrayIndex == byteArrayLength - 1)
        {
            byteTriplet = convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<= 4;
            byte b2 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b1 = (byte)(0x3f & byteTriplet);
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            result.append("==");
        }
        if(byteArrayIndex == byteArrayLength - 2)
        {
            byteTriplet = convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<= 8;
            byteTriplet |= convertUnsignedByteToInt(data[byteArrayIndex++]);
            byteTriplet <<= 2;
            byte b3 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b2 = (byte)(0x3f & byteTriplet);
            byteTriplet >>= 6;
            byte b1 = (byte)(0x3f & byteTriplet);
            result.append(mapByteToChar(b1));
            result.append(mapByteToChar(b2));
            result.append(mapByteToChar(b3));
            result.append("=");
        }
        return result.toString();
    }

    private char mapByteToChar(byte b)
    {
        if(b < 26)
            return (char)(65 + b);
        if(b < 52)
            return (char)(97 + (b - 26));
        if(b < 62)
            return (char)(48 + (b - 52));
        if(b == 62)
            return '+';
        if(b == 63)
            return '/';
        else
            throw new IllegalArgumentException("Byte " + new Integer(b) + " is not a valid Base64 value");
    }

    public static void main(String args[])
        throws Exception
    {
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        for(int j = 0; j < 100; j++)
        {
            byte test[] = new byte[(int)(100000D * Math.random())];
            for(int i = 0; i < test.length; i++)
                test[i] = (byte)(int)(256D * Math.random());

            String string = encoder.encode(test);
            byte result[] = decoder.decodeBuffer(string);
            if(!Arrays.equals(test, result) || test.length != result.length)
                System.out.println("ARRAYS DO NOT MATCH!");
        }

    }

    static final int LOWER_CASE_A_VALUE = 26;
    static final int ZERO_VALUE = 52;
    static final int PLUS_VALUE = 62;
    static final int SLASH_VALUE = 63;
    private static final int SIX_BIT_MASK = 63;
}
