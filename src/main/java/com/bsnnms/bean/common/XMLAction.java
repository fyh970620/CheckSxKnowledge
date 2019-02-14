package com.bsnnms.bean.common;

import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;

public interface XMLAction
{
    public String actionProcessRs(String value, String tagName, ResultSet rs)
            throws ApplicationException;
}
