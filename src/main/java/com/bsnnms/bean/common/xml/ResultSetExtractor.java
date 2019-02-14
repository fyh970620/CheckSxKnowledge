package com.bsnnms.bean.common.xml;

import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor
{
    String extractData(ResultSet rs, XMLResult xrs) throws SQLException, ApplicationException;
}
