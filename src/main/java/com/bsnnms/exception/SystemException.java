/**************************************************
 * Copyright (c) 2005.
 * 文件名称: SystemException.java
 * 摘　　要: 抛出系统级的异常,异常信息是来自数据库的错误代码
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-2
 **************************************************/
package com.bsnnms.exception;

import java.sql.SQLException;

public class SystemException extends BaseException
{
    public SystemException(String errorCode, Throwable ex)
    {
        super(errorCode, ex);
    }

    public SystemException(String errorCode)
    {
        super(errorCode);
    }

    public SystemException(SQLException ex)
    {
        super(ex);
    }
}
