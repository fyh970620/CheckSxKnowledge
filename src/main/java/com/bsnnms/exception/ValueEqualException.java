/**************************************************
 * Copyright (c) 2005.
 * 文件名称: BaseException.java
 * 摘　　要: 定义自己异常的基类,使用Throwable形成异常链
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-2
 **************************************************/
package com.bsnnms.exception;

import java.sql.SQLException;

public class ValueEqualException extends Exception
{
    public ValueEqualException(String errorCode)
    {
        super(errorCode);
    }

    public ValueEqualException(String errorCode, Throwable ex)
    {
        super(errorCode, ex);
    }

    public ValueEqualException(Throwable ex)
    {
        super(ex);
    }

    public ValueEqualException(SQLException ex)
    {
        super("380050", ex);
    }

    public String getErrMsg()
    {
        return super.getMessage();
    }

    public String getMessage()
    {
        Throwable cause = this.getCause();
        StringBuffer sReturn = new StringBuffer(super.getMessage());
        if (cause != null)
        {
            sReturn.append(":").append(cause.getMessage());
        }
        return sReturn.toString();
    }
}
