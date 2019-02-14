package com.bsnnms.bean.common.xml;

import oracle.jdbc.OracleTypes;

public class ColumnInfo
{
    private int     index;

    private String  name;

    private String  label;

    private int     type;

    private boolean isShow = true;

    public ColumnInfo()
    {}

    public ColumnInfo(int index, String name)
    {
        this.index = index;
        this.name = name;
    }

    public ColumnInfo(int index, String name, int type)
    {
        this(index, name);
        this.type = type;
    }

    public ColumnInfo(int index, String name, int type, String label)
    {
        this(index, name, type);
        this.label = label;
    }

    public ColumnInfo(String name, String label)
    {
        this.name = name;
        this.label = label;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public boolean isShow()
    {
        return isShow;
    }

    public void setShow(boolean isShow)
    {
        this.isShow = isShow;
    }

    public String getTypeName()
    {
        return (this.type == OracleTypes.NUMBER) ? "float" : "string";
    }
}
