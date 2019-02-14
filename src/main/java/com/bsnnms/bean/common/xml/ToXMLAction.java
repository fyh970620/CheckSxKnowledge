package com.bsnnms.bean.common.xml;

import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;

public interface ToXMLAction
{
    public void setTagName(String tagName);

    public void setValue(String value);

    public void setRs(ResultSet rs);

    public boolean transformRs() throws ApplicationException;

    public String getValue();

    public String getTagName();
}
