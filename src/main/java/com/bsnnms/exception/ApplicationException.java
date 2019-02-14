/**************************************************
 * Copyright (c) 2005.
 * 文件名称: ApplicationException.java
 * 摘　　要: 抛出应用级的异常,异常信息来自抱出时填写的信息
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-2
 **************************************************/
package com.bsnnms.exception;

public class ApplicationException extends BaseException
{
    String[] errs = {};

    public ApplicationException(String errorCode)
    {
        super(errorCode);
    }

    public ApplicationException(String errorCode, Throwable ex)
    {
        super(errorCode, ex);
    }

    public ApplicationException(String errorCode, Throwable ex, String[] errs)
    {
        super(errorCode, ex);
        this.errs = errs;
    }

    public void outErrorMsg()
    {
        for (int i = 0, len = errs.length; i < len; i++)
        {
            System.out.println(errs[i]);
        }
    }
}
