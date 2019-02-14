package com.bsnnms.bean.common.xml;

import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class ExtractorAction
{
    private final int[] keys;

    private final int[] hiddens;

    public ExtractorAction(int[] keys, int[] hiddens)
    {
        this.keys = keys;
        this.hiddens = hiddens;
    }

    public ExtractorAction()
    {
        this(new int[0], new int[0]);
    }

    public int[] getKeys()
    {
        return this.keys;
    }

    public int[] getHiddens()
    {
        return this.hiddens;
    }

    protected abstract ColumnInfo createColumnInfo(ResultSetMetaData rsmd,
            int index) throws SQLException;

    protected abstract boolean isAddField();

    protected abstract String columnConvert(ColumnInfo column, String tagName);

    protected abstract String getKeyName(ColumnInfo keyColumn);

    protected abstract String rowConvert(ColumnInfo column, ResultSet rs,
            ToXMLAction toXMLAction) throws SQLException, ApplicationException;

}
