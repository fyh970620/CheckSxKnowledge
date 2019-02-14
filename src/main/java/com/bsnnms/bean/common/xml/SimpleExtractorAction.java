package com.bsnnms.bean.common.xml;

import com.bsnnms.bean.common.StringTemplate;
import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class SimpleExtractorAction extends ExtractorAction
{
    private final boolean               isAddField;

    private final static StringTemplate COLUMN_TEMPLATE = new StringTemplate(
                                                                "<{$tag} index=\"{$index}\">{$name}</{$tag}>");

    public SimpleExtractorAction(int[] keys, int hiddens[], boolean isAddField)
    {
        super(keys, hiddens);
        this.isAddField = isAddField;
    }

    public SimpleExtractorAction(boolean isAddField)
    {
        super();
        this.isAddField = isAddField;
    }

    public SimpleExtractorAction()
    {
        this(false);
    }

    public String columnConvert(ColumnInfo column, String tagName)
    {
        HashMap attrs = new HashMap();
        attrs.put("tag", tagName);
        attrs.put("index", Integer.toString(column.getIndex()));
        attrs.put("name", column.getName());
        return COLUMN_TEMPLATE.apply(attrs);
    }

    public ColumnInfo createColumnInfo(ResultSetMetaData rsmd, int index)
            throws SQLException
    {
        return new ColumnInfo(index, rsmd.getColumnName(index), rsmd
                .getColumnType(index));
    }

    public String getKeyName(ColumnInfo keyColumn)
    {
        return keyColumn.getName();
    }

    public boolean isAddField()
    {
        return this.isAddField;
    }

    public String rowConvert(ColumnInfo column, ResultSet rs,
            ToXMLAction toXMLAction) throws SQLException, ApplicationException
    {
        String name = column.getName();
        String value = XMLResult.getRsValue(rs, column);
        String xml = "";
        boolean isAppend = true;
        if (toXMLAction != null)
        {
            toXMLAction.setRs(rs);
            toXMLAction.setTagName(name);
            toXMLAction.setValue(value);
            isAppend = toXMLAction.transformRs();
            name = toXMLAction.getTagName();
            value = toXMLAction.getValue();
        }
        if (isAppend)
        {
            xml = ResultSetExtractorByAction.createXmlRow(name, value);
        }
        return xml;
    }
}
