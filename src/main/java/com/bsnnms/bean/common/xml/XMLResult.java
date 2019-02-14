package com.bsnnms.bean.common.xml;

import com.bsnnms.bean.common.StringTemplate;
import com.bsnnms.bean.common.StringUtils;
import com.bsnnms.bean.common.XML;
import com.bsnnms.exception.ApplicationException;
import oracle.jdbc.OracleTypes;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * XML记录流
 * </p>
 * 将SQLResult转化为XML数据流
 * 
 * @author 方旭尘
 * @version 1.0
 */
public class XMLResult
{
    private static final String    TREE_TAG_NAME      = "MenuItem";

    private ResultSet              rs;

    private int                    rowCount           = -1;

    private String                 filerXpath;

    private boolean                isFilter           = false;

    private boolean                isAddKey           = false;

    private boolean                isAddField         = false;

    private boolean                isComplete         = true;

    private boolean                isToLowerCase      = false;

    private boolean                isKeyToId          = true;

    private XMLTreeAttributeAction attAction;

    private SingleXMLAction        singleXMLAction;

    private ToXMLAction            toXMLAction;

    private XMLTreeRootAction      rootAction;

    private IsAddTreeNodeAction    isAddTreeNodeAction;

    private Element                menuRoot;

    private Element                treeRoot;

    private int                    icoColumnIndex     = 0;

    private String                 rowTagName         = "rowSet";

    private StringTemplate ARRAY_XML_TEMPLATE = new StringTemplate(
                                                              "<item>{$value}</item>");

    public XMLResult()
    {}

    public XMLResult(ResultSet _rs)
    {
        this.rs = _rs;
    }

    private Document completeXML()
    {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        Element errElement = root.addElement("error_code");
        errElement.setText("0");
        root.add(this.treeRoot);
        if (isFilter)
        {
            List filterList = doc.selectNodes(filerXpath);
            Element filterItem;
            for (int i = 0; i < filterList.size(); i++)
            {
                filterItem = (Element) filterList.get(i);
                filterItem.detach();
            }
        }
        return doc;
    }

    public Element completeXML(Element el)
    {
        Element root;
        if (el.getName().equals("root"))
        {
            root = el;
        }
        else
        {
            root = DocumentHelper.createElement("root");
            root.add(el);
        }
        Element errorCode = root.addElement("error_code");
        errorCode.setText("0");
        return root;
    }

    public String completeXML(String xml)
    {
        return completeXML(new StringBuffer(xml));
    }

    public String completeXML(StringBuffer xml)
    {
        if (this.rowCount != -1)
        {
            xml.append("<recordCount>");
            xml.append(this.rowCount);
            xml.append("</recordCount>");
        }
        if (this.isComplete)
        {
            StringBuffer xmlReturn = new StringBuffer("<root>");
            xmlReturn.append("<error_code>0</error_code>");
            xmlReturn.append(xml);
            xmlReturn.append("</root>");
            return xmlReturn.toString();
        }
        else
        {
            return xml.toString();
        }
    }

    private ColumnInfo[] doAttName(int idIndex, int nameIndex,
            ResultSetMetaData rsmd) throws SQLException
    {
        int columnCount = rsmd.getColumnCount();
        ColumnInfo column;
        ColumnInfo[] columns = new ColumnInfo[columnCount + 2];
        column = new ColumnInfo(idIndex, "id");
        columns[0] = column;
        column = new ColumnInfo(nameIndex, "label");
        columns[1] = column;
        for (int i = 1; i <= columnCount; i++)
        {
            column = new ColumnInfo();
            column.setIndex(i);
            if (i == icoColumnIndex)
            {
                column.setName("ico");
            }
            else
            {
                column.setName(doAttName(rsmd.getColumnLabel(i)));
            }
            columns[i + 1] = column;
        }
        return columns;
    }

    private String doAttName(String attName)
    {
        if (this.isToLowerCase)
        {
            attName = attName.toLowerCase();
        }
        return attName;
    }

    private Element getMenuRoot()
    {
        if (this.menuRoot == null)
        {
            this.menuRoot = DocumentHelper.createElement("Menu");
            this.treeRoot = this.menuRoot;
            if (this.rootAction != null)
            {
                this.menuRoot = rootAction.doRoot(this.menuRoot);
            }
        }
        return this.menuRoot;
    }

    public boolean isComplete()
    {
        return isComplete;
    }

    public void setComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public void setRowCount(int rowCount)
    {
        this.rowCount = rowCount;
    }

    public boolean isToLowerCase()
    {
        return isToLowerCase;
    }

    public void setToLowerCase(boolean isToLowerCase)
    {
        this.isToLowerCase = isToLowerCase;
    }

    public void setAttAction(XMLTreeAttributeAction attAction)
    {
        this.attAction = attAction;
    }

    public void setRootAction(XMLTreeRootAction rootAction)
    {
        this.rootAction = rootAction;
    }

    public void setSingleXMLAction(SingleXMLAction singleXMLAction)
    {
        this.singleXMLAction = singleXMLAction;
    }

    public void setToXMLAction(ToXMLAction toXMLAction)
    {
        this.toXMLAction = toXMLAction;
    }

    public ToXMLAction getToXMLAction()
    {
        return toXMLAction;
    }

    public IsAddTreeNodeAction getIsAddTreeNodeAction()
    {
        return isAddTreeNodeAction;
    }

    public void setIsAddTreeNodeAction(IsAddTreeNodeAction isAddTreeNodeAction)
    {
        this.isAddTreeNodeAction = isAddTreeNodeAction;
    }

    public boolean isAddKey()
    {
        return isAddKey;
    }

    public void setAddKey(boolean isAddKey)
    {
        this.isAddKey = isAddKey;
    }

    public boolean isAddField()
    {
        return isAddField;
    }

    public void setAddField(boolean isAddField)
    {
        this.isAddField = isAddField;
    }

    public ResultSet getRs()
    {
        return rs;
    }

    public void setRs(ResultSet rs)
    {
        this.rs = rs;
    }

    public int getIcoColumnIndex()
    {
        return icoColumnIndex;
    }

    public void setIcoColumnIndex(int icoColumnIndex)
    {
        this.icoColumnIndex = icoColumnIndex;
    }

    public String toSingleXML(String tagName, int text)
    {
        return toSingleXML(tagName, Integer.toString(text));
    }

    public String toSingleXML(String tagName, String text)
    {
        StringBuffer singleXML = new StringBuffer("");
        singleXML.append("<").append(tagName).append(">");
        singleXML.append(XML.Encode(text));
        singleXML.append("</").append(tagName).append(">");
        return this.completeXML(singleXML);
    }

    public String toSingleXML() throws ApplicationException
    {
        return toSingleXML(true);
    }

    public String toSingleXML(boolean isAddRoot) throws ApplicationException
    {
        StringBuffer xmlReturn = new StringBuffer();
        if (isAddRoot)
        {
            xmlReturn.append("<Msg>");
        }
        String tagName;
        String value;
        boolean isAppend = true;
        try
        {
            ResultSetMetaData rsmd = this.rs.getMetaData();
            if (this.rs.next())
            {
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                {
                    tagName = XML.Encode(rsmd.getColumnLabel(i));
                    value = this.rs.getString(i);
                    if (this.singleXMLAction != null)
                    {
                        isAppend = this.singleXMLAction.doAction(xmlReturn,
                                tagName, value);
                    }
                    if (isAppend)
                    {
                        xmlReturn.append("<").append(tagName);
                        if (value == null)
                        {
                            xmlReturn.append("/>");
                        }
                        else
                        {
                            xmlReturn.append(">");
                            xmlReturn.append(XML.Encode(value));
                            xmlReturn.append("</").append(tagName).append(">");
                        }
                    }
                }
            }
            if (isAddRoot)
            {
                xmlReturn.append("</Msg>");
            }
            return completeXML(xmlReturn);
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取记录集错误!", e);
        }
    }

    public String toXMLAtt() throws ApplicationException
    {
        StringBuffer xmlReturn = new StringBuffer("");
        int i = 0;
        try
        {
            ResultSetMetaData rsmd = this.rs.getMetaData();
            String[] labels = new String[rsmd.getColumnCount()];
            String attName;
            for (i = 0; i < labels.length; i++)
            {
                labels[i] = rsmd.getColumnLabel(i + 1);
            }
            while (this.rs.next())
            {
                xmlReturn.append("<").append(rowTagName).append(" ");
                for (i = 1; i <= labels.length; i++)
                {
                    attName = XML.Encode(labels[i - 1]);
                    xmlReturn.append(doAttName(attName)).append("=\"");
                    xmlReturn.append(XML.Encode(this.rs.getString(i)));
                    xmlReturn.append("\" ");
                }
                xmlReturn.append("/>");
            }
            return completeXML(xmlReturn);
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取记录集错误!", e);
        }
    }

    public String toXML() throws ApplicationException
    {
        return this.toXML(new int[0], new int[0]);
    }

    public String toXML(int key[]) throws ApplicationException
    {
        return this.toXML(key, new int[0]);
    }

    public String toXML(int key) throws ApplicationException
    {
        return this.toXML(key, new int[0]);
    }

    public String toXML(int key, int hidden[]) throws ApplicationException
    {
        int[] keys;
        if (key == 0)
        {
            keys = new int[0];
        }
        else
        {
            keys = new int[1];
            keys[0] = key;
        }
        return this.toXML(keys, hidden);
    }

    public String toXML(int key[], int hidden[]) throws ApplicationException
    {
        ExtractorAction action = new SimpleExtractorAction(key, hidden,
                this.isAddField);
        return toXML(action);
    }

    public String toXML(ExtractorAction action) throws ApplicationException
    {
        ResultSetExtractor rse = new ResultSetExtractorByAction(action);
        return toXML(rse);
    }

    public String toXML(ResultSetExtractor rse) throws ApplicationException
    {
        try
        {
            return rse.extractData(this.rs, this);
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取记录集错误!", e);
        }
    }

    public String toXML(String[] values)
    {
        StringBuffer sbuf = new StringBuffer("");
        HashMap attrs = new HashMap();
        for (int i = 0, len = values.length; i < len; i++)
        {
            attrs.put("value", values[i]);
            sbuf.append(ARRAY_XML_TEMPLATE.apply(attrs));
        }
        return this.completeXML(sbuf);
    }

    public static String getRsValue(ResultSet rs, ColumnInfo column)
            throws SQLException
    {
        String value;
        if (column.getType() == OracleTypes.VARCHAR ||column.getType() == OracleTypes.CLOB)
        {
            value = rs.getString(column.getIndex());
            value = StringUtils.toNoNull(value);
        }
        else
        {
        	Object obj = rs.getObject(column.getIndex());
          if (obj !=null && "weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB".equals(obj.getClass().getName()))
          {
            	value = rs.getString(column.getIndex());
            	value = StringUtils.toNoNull(value);
          }
          else
          {
	            value = (obj == null) ? "" : obj.toString();
          }
        }
        return value;
    }

    public void toTreeNoReturn(int idIndex, int nameIndex, int levelIndex)
            throws ApplicationException
    {
        int i = 0;
        this.menuRoot = getMenuRoot();
        List parents = new ArrayList();
        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            ColumnInfo[] columns = this.doAttName(idIndex, nameIndex, rsmd);
            ColumnInfo column;
            boolean isAdd = true;
            int noAddLevel = Integer.MAX_VALUE;
            while (rs.next())
            {
                int level = rs.getInt(levelIndex);
                if (parents.isEmpty())
                {
                    for (i = 0; i < level - 1; i++)
                    {
                        parents.add(null);
                    }
                    parents.add(this.menuRoot);
                }
                if (isAdd || level <= noAddLevel)
                {
                    if (this.isAddTreeNodeAction != null)
                    {
                        isAdd = this.isAddTreeNodeAction.isAdd(rs);
                    }
                    if (isAdd)
                    {
                        Element item = DocumentHelper
                                .createElement(TREE_TAG_NAME);
                        for (i = 0; i < columns.length; i++)
                        {
                            column = columns[i];
                            item.addAttribute(column.getName(), rs
                                    .getString(column.getIndex()));
                        }
                        if (this.attAction != null)
                        {
                            this.attAction.setAttribute(item);
                        }
                        Element parent = (Element) parents.get(level - 1);
                        parent.add(item);
                        parents.add(level, item);
                    }
                    else
                    {
                        noAddLevel = level;
                    }
                }
            }
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取记录集错误!", e);
        }
    }

    public Document toTree(int idIndex, int nameIndex, int levelIndex)
            throws ApplicationException
    {
        toTreeNoReturn(idIndex, nameIndex, levelIndex);
        return this.completeXML();
    }

    public Document toTreeNoLevel(int idIndex, int parentIndex, int nameIndex)
            throws ApplicationException
    {
        Document docReturn = toTree(idIndex, nameIndex);
        List menuItems = this.menuRoot.elements();
        if (menuItems.size() > 0)
        {
            Element item = (Element) this.menuRoot.elements().get(0);
            while (item != null)
            {
                setChild(item, parentIndex + 2);
                item = (Element) item
                        .selectSingleNode("following-sibling::MenuItem[position()=1]");
            }
        }
        return docReturn;
    }

    public Document toTree(int idIndex, int nameIndex, String parentName)
            throws ApplicationException
    {
        Document docReturn = toTree(idIndex, nameIndex);
        List menuItems = this.menuRoot.elements();
        if (menuItems.size() > 0)
        {
            Element item = (Element) this.menuRoot.elements().get(0);
            while (item != null)
            {
                setChild(item, parentName);
                item = (Element) item
                        .selectSingleNode("following-sibling::MenuItem[position()=1]");
            }
        }
        return docReturn;
    }

    public Document parseText(String xml) throws ApplicationException
    {
        Document docReturn;
        try
        {
            docReturn = DocumentHelper.parseText(xml);
        }
        catch (DocumentException e)
        {
            throw new ApplicationException("转换为doc错误!", e);
        }
        return docReturn;
    }

    private void setChild(Element parentItem, int parentIndex)
    {
        StringBuffer xPath = new StringBuffer("MenuItem[@*[");
        xPath.append(parentIndex);
        xPath.append("]='");
        xPath.append(parentItem.attributeValue("id"));
        xPath.append("']");
        List childs = this.menuRoot.selectNodes(xPath.toString());
        for (int i = 0; i < childs.size(); i++)
        {
            Element oItem = (Element) childs.get(i);
            this.menuRoot.remove(oItem);
            parentItem.add(oItem);
            setChild(oItem, parentIndex);
        }
    }

    private void setChild(Element parentItem, String parentName)
    {
        StringBuffer xPath = new StringBuffer("MenuItem[@");
        xPath.append(parentName);
        xPath.append("='");
        xPath.append(parentItem.attributeValue("id"));
        xPath.append("']");
        List childs = this.menuRoot.selectNodes(xPath.toString());
        for (int i = 0; i < childs.size(); i++)
        {
            Element oItem = (Element) childs.get(i);
            this.menuRoot.remove(oItem);
            parentItem.add(oItem);
            setChild(oItem, parentName);
        }
    }

    public void toTreeNoReturn(int idIndex, int nameIndex)
            throws ApplicationException
    {
        int i = 0;
        this.menuRoot = getMenuRoot();
        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            ColumnInfo[] columns = this.doAttName(idIndex, nameIndex, rsmd);
            ColumnInfo column;
            while (rs.next())
            {
                Element item = DocumentHelper.createElement(TREE_TAG_NAME);
                for (i = 0; i < columns.length; i++)
                {
                    column = columns[i];
                    item.addAttribute(column.getName(), rs.getString(column
                            .getIndex()));
                }
                if (this.attAction != null)
                {
                    this.attAction.setAttribute(item);
                }
                this.menuRoot.add(item);
            }
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取记录集错误!", e);
        }
    }

    public Document toTree(int idIndex, int nameIndex)
            throws ApplicationException
    {
        toTreeNoReturn(idIndex, nameIndex);
        return this.completeXML();
    }

    public StringBuffer outXML(Document doc) throws ApplicationException
    {
        return this.outXML(doc.getRootElement());
    }

    public StringBuffer outXML(Element oElement) throws ApplicationException
    {
        StringWriter sWrite = new StringWriter();
        OutputFormat out = new OutputFormat();
        out.setEncoding("GB2312");
        XMLWriter output = new XMLWriter(sWrite, out);
        try
        {
            output.write(oElement);
        }
        catch (IOException e)
        {
            throw new ApplicationException("格式化输出XML错误!", e);
        }
        return sWrite.getBuffer();
    }

    public StringBuffer outXML(Element oElement,String encode) throws ApplicationException
    {
        StringWriter sWrite = new StringWriter();
        OutputFormat out = new OutputFormat();
        out.setEncoding(encode);
        XMLWriter output = new XMLWriter(sWrite, out);
        try
        {
            output.write(oElement);
        }
        catch (IOException e)
        {
            throw new ApplicationException("格式化输出XML错误!", e);
        }
        return sWrite.getBuffer();
    }

    public String getFilerXpath()
    {
        return filerXpath;
    }

    public void setFilerXpath(String filerXpath)
    {
        this.filerXpath = filerXpath;
        this.isFilter = true;
    }

    public void setFilterXpath(String attName, String val)
    {
        StringBuffer xPath = new StringBuffer("//MenuItem");
        xPath.append("[@").append(attName);
        xPath.append("='").append(val).append("']");
        setFilerXpath(xPath.toString());
    }

    public String getRowTagName()
    {
        return rowTagName;
    }

    public void setRowTagName(String rowTagName)
    {
        this.rowTagName = rowTagName;
    }

    public boolean isKeyToId()
    {
        return isKeyToId;
    }

    public void setKeyToId(boolean isKeyToId)
    {
        this.isKeyToId = isKeyToId;
    }
}
