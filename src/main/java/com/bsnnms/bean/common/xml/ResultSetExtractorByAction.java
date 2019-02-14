package com.bsnnms.bean.common.xml;

import com.bsnnms.bean.common.StringTemplate;
import com.bsnnms.bean.common.XML;
import com.bsnnms.exception.ApplicationException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class ResultSetExtractorByAction implements ResultSetExtractor
{
    private ExtractorAction action;

    private final int[]                 keys;

    private final int[]                 hiddens;

    private final static StringTemplate XML_ATTR_TEMPLATE = new StringTemplate(
                                                                  " {$name}=\"{$value}\"");

    private final static StringTemplate XML_ROW_TEMPLATE  = new StringTemplate(
                                                                  "<{$name}>{$value}</{$name}>");

    public ResultSetExtractorByAction(ExtractorAction action)
    {
        this.action = action;
        this.keys = action.getKeys();
        this.hiddens = action.getHiddens();
    }

    private static String createXmlByTemplate(String name, String value,
            StringTemplate template)
    {
        HashMap attrs = new HashMap();
        attrs.put("name", name);
        attrs.put("value", XML.Encode(value));
        return template.apply(attrs);
    }

    public static String createXmlAttr(String name, String value)
    {
        return createXmlByTemplate(name, value, XML_ATTR_TEMPLATE);
    }

    public static String createXmlRow(String name, String value)
    {
        return createXmlByTemplate(name, value, XML_ROW_TEMPLATE);
    }

    private ColumnInfo[] createColumnsByIndexArray(ColumnInfo[] columns,
            int[] indexs, boolean isShow)
    {
        int len = indexs.length;
        ColumnInfo[] returnColumns = new ColumnInfo[len];
        ColumnInfo column;
        for (int i = 0; i < len; i++)
        {
            column = columns[indexs[i] - 1];
            column.setShow(isShow);
            returnColumns[i] = column;
        }
        return returnColumns;
    }

    public String extractData(ResultSet rs, XMLResult xrs) throws SQLException, ApplicationException
    {
        StringBuffer sXml = new StringBuffer("");
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount(), i;
        ColumnInfo column;
        ColumnInfo[] columns = new ColumnInfo[columnCount];
        String value, name, xmlValue;
        for (i = 0; i < columnCount; i++)
        {
            columns[i] = this.action.createColumnInfo(rsmd, i + 1);
        }
        ColumnInfo[] keyColumns = this.createColumnsByIndexArray(columns,
                this.keys, xrs.isAddKey());
        this.createColumnsByIndexArray(columns, this.hiddens, false);
        while (rs.next())
        {
            sXml.append("<").append(xrs.getRowTagName());
            if (keyColumns.length == 1 && xrs.isKeyToId())
            {
                name = "id";
                value = XMLResult.getRsValue(rs, keyColumns[0]);
                xmlValue = ResultSetExtractorByAction
                        .createXmlAttr(name, value);
                sXml.append(xmlValue);
            }
            else
            {
                for (i = 0; i < keyColumns.length; i++)
                {
                    column = keyColumns[i];
                    name = this.action.getKeyName(column);
                    value = XMLResult.getRsValue(rs, column);
                    xmlValue = ResultSetExtractorByAction.createXmlAttr(name,
                            value);
                    sXml.append(xmlValue);
                }
            }
            sXml.append(">");
            for (i = 0; i < columns.length; i++)
            {
                column = columns[i];
                if (column.isShow())
                {
                    xmlValue = this.action.rowConvert(column, rs, xrs
                            .getToXMLAction());
                    sXml.append(xmlValue);
                }
            }
            sXml.append("</").append(xrs.getRowTagName()).append(">");
        }
        if (this.action.isAddField())
        {
            sXml.append("<Fields>");
            for (i = 0; i < columnCount; i++)
            {
                column = columns[i];
                if (column.isShow())
                {
                    sXml.append(this.action.columnConvert(column, "FLDNAME"));
                }
            }
            sXml.append("</Fields>");
        }
        return xrs.completeXML(sXml);
    }
}
