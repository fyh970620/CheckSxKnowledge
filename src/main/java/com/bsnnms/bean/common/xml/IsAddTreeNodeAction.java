package com.bsnnms.bean.common.xml;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IsAddTreeNodeAction
{
    public boolean isAdd(ResultSet rs) throws SQLException;
}
