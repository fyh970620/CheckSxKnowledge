package com.ffcs.itm.web.basic.support;

import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.bean.common.xml.XMLResult;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StrategyConfig
{
    private static final String SQL_GET_STRATEGY_CONFIG = "SELECT STRATEGY_VALUE FROM STRATEGY_CONFIG WHERE STATE = '0SA' AND STRATEGY_ID=?";

    private static final String SQL_SET_STRATEGY_CONFIG = "UPDATE STRATEGY_CONFIG SET STATE = ?,STRATEGY_VALUE = ? WHERE STRATEGY_ID = ?";

    private static final String SQL_GET_STRATEGY_LIST   = "SELECT STRATEGY_ID,STATE,TYPE,STRATEGY_NAME,STRATEGY_VALUE,STRATEGY_REMARK,DECODE(STATE,'0SA','已启用','已禁用') STATE_TEXT FROM STRATEGY_CONFIG WHERE GROUP_STRATEGY_ID = ? ORDER BY SORT_ID";

    private String              value;

    private String              key;

    private String              state;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public int getValueByInt()
    {
        return Integer.parseInt(value);
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public boolean hasStrategy() throws ApplicationException, SystemException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isHas = false;
        try {
            pstmt = conn.prepareStatement(SQL_GET_STRATEGY_CONFIG);
            pstmt.setString(1, this.key);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                this.value = rs.getString(1);
                isHas = true;
            }
            return isHas;
        }
        catch (SQLException e) {
            throw new ApplicationException("获取策略信息错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
    }

    public void setStrategyConfig() throws ApplicationException, SystemException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL_SET_STRATEGY_CONFIG);
            pstmt.setString(1, this.state);
            pstmt.setString(2, this.value);
            pstmt.setString(3, this.key);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new ApplicationException("设置策略值错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt);
        }
    }

    public String getStrategyByGroup(int groupId) throws ApplicationException, SystemException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        XMLResult xRs;
        try {
            pstmt = conn.prepareStatement(SQL_GET_STRATEGY_LIST);
            pstmt.setInt(1, groupId);
            rs = pstmt.executeQuery();
            xRs = new XMLResult();
            xRs.setRs(rs);
            int[] key = {1, 2, 3};
            return xRs.toXML(key);
        }
        catch (SQLException e) {
            throw new ApplicationException("获取策略信息错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
    }
}
