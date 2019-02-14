package com.bsnnms.bean.common.security;

public class BASE64Decoder
{
    private class StringWrapper
    {
        private boolean isUsefulChar(char c)
        {
            return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '+' || c == '/';
        }

        public int getUsefulLength()
        {
            return mUsefulLength;
        }

        public char getNextUsefulChar()
        {
            char result;
            for(result = '_'; !isUsefulChar(result); result = mString.charAt(mIndex++));
            return result;
        }

        private String mString;
        private int mIndex;
        private int mUsefulLength;

        public StringWrapper(String s)
        {
            super();
            mIndex = 0;
            mString = s;
            mUsefulLength = 0;
            int length = mString.length();
            for(int i = 0; i < length; i++)
                if(isUsefulChar(mString.charAt(i)))
                    mUsefulLength++;

        }
    }


    public BASE64Decoder()
    {
    }

    public byte[] decodeBuffer(String data)
    {
        StringWrapper wrapper = new StringWrapper(data);
        int byteArrayLength = (wrapper.getUsefulLength() * 3) / 4;
        byte result[] = new byte[byteArrayLength];
        int byteTriplet = 0;
        int byteIndex;
        for(byteIndex = 0; byteIndex + 2 < byteArrayLength; byteIndex += 3)
        {
            byteTriplet = mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            result[byteIndex + 2] = (byte)(byteTriplet & 0xff);
            byteTriplet >>= 8;
            result[byteIndex + 1] = (byte)(byteTriplet & 0xff);
            byteTriplet >>= 8;
            result[byteIndex] = (byte)(byteTriplet & 0xff);
        }

        if(byteIndex == byteArrayLength - 1)
        {
            byteTriplet = mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet >>= 4;
            result[byteIndex] = (byte)(byteTriplet & 0xff);
        }
        if(byteIndex == byteArrayLength - 2)
        {
            byteTriplet = mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet <<= 6;
            byteTriplet |= mapCharToInt(wrapper.getNextUsefulChar());
            byteTriplet >>= 2;
            result[byteIndex + 1] = (byte)(byteTriplet & 0xff);
            byteTriplet >>= 8;
            result[byteIndex] = (byte)(byteTriplet & 0xff);
        }
        return result;
    }

    private int mapCharToInt(char c)
    {
        if(c >= 'A' && c <= 'Z')
            return c - 65;
        if(c >= 'a' && c <= 'z')
            return (c - 97) + 26;
        if(c >= '0' && c <= '9')
            return (c - 48) + 52;
        if(c == '+')
            return 62;
        if(c == '/')
            return 63;
        else
            throw new IllegalArgumentException(c + " is not a valid Base64 character.");
    }

    private static final int EIGHT_BIT_MASK = 255;
}
