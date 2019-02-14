/**************************************************
 * Copyright (c) 2005.
 * 文件名称: DBCtrl.java
 * 摘　　要: 提供数据库一些通用操作,如得到数据库连接,关闭数据库连接等等
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-8-2
 **************************************************/
package com.bsnnms.bean.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;
import oracle.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <p>
 * 数据库实用操作
 * </p>
 * 提供数据库一些通用操作,如得到数据库连接,关闭数据库连接等等
 *
 * @author 方旭尘
 * @version 1.0
 */
@Component
public class DBCtrl
{
    private static DataSource dataSource;

    @Autowired
    private Environment env;

    @Autowired(required = true)
    public void setDataSource(DataSource dataSource) {
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setUrl(env.getProperty("spring.datasource.url"));
        dataSource2.setUsername(env.getProperty("spring.datasource.username"));
        dataSource2.setPassword(env.getProperty("spring.datasource.password"));
        DBCtrl.dataSource = dataSource;
    }

    private static HashMap      DATASOURCE_MAP;

    /**
     * 初始化JNDI,清空HashMap中的内容
     */
    public static void initial()
    {
        if (DATASOURCE_MAP != null)
        {
            DATASOURCE_MAP.clear();
        }
    }

    /**
     * 初始化WEB容器
     *
     * @return WEB容器对象
     * @throws SystemException
     */
    public static Context getContext() throws SystemException
    {
        Context ctx = null;
        try
        {
            ctx = new InitialContext();
        }
        catch (NamingException e)
        {
            throw new SystemException("380001", e);
        }
        return ctx;
    }

    /**
     * 得到指定jndi绑定的数据源对象
     *
     * @param dsJndi
     *            数据源jndi地址
     * @return 数据源连接池对象
     * @throws SystemException
     */
    public static DataSource getDataSource(String dsJndi)
            throws SystemException
    {
        DataSource ds = null;
        Context ctx;
        try
        {
            // 连接池缓存对象不存在
            if (DATASOURCE_MAP == null)
            {
                DATASOURCE_MAP = new HashMap();
                ctx = getContext();
                ds = (DataSource) ctx.lookup(dsJndi);
                DATASOURCE_MAP.put(dsJndi, ds);
            }
            // 存在连接池缓存对象
            else
            {
                // 连接池缓存对象中存在指定的连接池
                if (DATASOURCE_MAP.containsKey(dsJndi))
                {
                    ds = (DataSource) DATASOURCE_MAP.get(dsJndi);
                }
                // 连接池缓存对象中查找不到指定的连接池
                else
                {
                    ctx = getContext();
                    ds = (DataSource) ctx.lookup(dsJndi);
                    DATASOURCE_MAP.put(dsJndi, ds);
                }
            }
        }
        catch (NamingException e)
        {
            throw new SystemException("380003", e);
        }
        return ds;
    }

    /**
     * 根据程序内部默认的连接池名返回数据库连接
     *
     * @return 数据库连接对象
     * @throws SystemException
     */
    public static Connection getConnection() throws SystemException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new SystemException("获取数据库连接失败!", e);
        }
    }

    public static Connection getConnectionByJndi(String dsJndi)
            throws SystemException
    {
        try
        {
            DataSource ds = getDataSource(dsJndi);
            return ds.getConnection();
        }
        catch (SQLException e)
        {
            throw new SystemException("380004", e);
        }
    }


    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @throws SystemException
     */
    public static void close(Connection conn) throws SystemException
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param pstmt
     *            数据库预处理对象
     * @param rs
     *            数据记录集合
     * @throws SystemException
     */
    public static void close(PreparedStatement pstmt, ResultSet rs)
            throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (pstmt != null)
            {
                pstmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param cstmt
     *            数据库预处理对象
     * @param rs
     *            数据记录集合
     * @throws SystemException
     */
    public static void close(CallableStatement cstmt, ResultSet rs)
            throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (cstmt != null)
            {
                cstmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
    }

    public static void close(CallableStatement cstmt) throws SystemException
    {
        try
        {
            if (cstmt != null)
            {
                cstmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param pstmt
     *            数据库预处理对象
     * @param rs
     *            数据记录集合
     * @throws SystemException
     */
    public static void close(Connection conn, PreparedStatement pstmt,
                             ResultSet rs, CallableStatement csmt) throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (pstmt != null)
            {
                pstmt.close();
            }
            if (csmt != null)
            {
                csmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    public static void close(Connection conn, PreparedStatement pstmt,
                             ResultSet rs) throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (pstmt != null)
            {
                pstmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    public static void close(Connection conn, ResultSet rs)
            throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param stmt
     *            数据库查询对象
     * @param rs
     *            记录集合
     * @throws SystemException
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs)
            throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (stmt != null)
            {
                stmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param pstmt
     *            执行对象
     * @throws SystemException
     */
    public static void close(Connection conn, PreparedStatement pstmt)
            throws SystemException
    {
        try
        {
            if (pstmt != null)
            {
                pstmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param stmt
     *            执行对象
     * @throws SystemException
     */
    public static void close(Connection conn, Statement stmt)
            throws SystemException
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param ctmt
     *            执行对象
     * @throws SystemException
     */
    public static void close(Connection conn, CallableStatement ctmt)
            throws SystemException
    {
        try
        {
            if (ctmt != null)
            {
                ctmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    /**
     * 关闭回收数据库连接等资源
     *
     * @param conn
     *            数据库连接
     * @param ctmt
     *            查询对象
     * @param rs
     *            记录集合
     * @throws SystemException
     */
    public static void close(Connection conn, CallableStatement ctmt,
                             ResultSet rs) throws SystemException
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
            if (ctmt != null)
            {
                ctmt.close();
            }
        }
        catch (SQLException e)
        {
            throw new SystemException("380006", e);
        }
        finally
        {
            DBCtrl.close(conn);
        }
    }

    public static void rollback(Connection conn) throws ApplicationException
    {
        try
        {
            conn.rollback();
        }
        catch (SQLException e)
        {
            throw new ApplicationException("数据库事务回滚错误", e);
        }
    }

    public static void setAutoCommit(Connection conn) throws SystemException
    {
        try
        {
            conn.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            DBCtrl.close(conn);
            throw new SystemException("380006", e);
        }
    }


    public static String clobToString(CLOB clob) throws ApplicationException
    {
        try
        {
            String sClob = clob.getSubString((long) 1, (int) clob.length());
            // System.out.println(sClob);
            return sClob;
        }
        catch (SQLException e)
        {
            throw new ApplicationException("读取CLOB字段错误", e);
        }
    }

    public static void writeClob(CLOB clob, String str)
            throws ApplicationException
    {
        try
        {
            if (str != null && (str.length() < clob.length()))
            {
                clob.truncate(str.length());
            }
            Writer w = clob.setCharacterStream(1);
            w.write(str);
            w.flush();
            w.close();
        }
        catch (SQLException e)
        {
            throw new ApplicationException("获取CLOB字段错误", e);
        }
        catch (IOException e)
        {
            throw new ApplicationException("写入CLOB,IO错误", e);
        }
    }

    public static ARRAY getOracleArray(Map<String, String> objMap,Connection conn) throws ApplicationException {
        ARRAY array = null;
        try
        {
            StructDescriptor sd = StructDescriptor.createDescriptor("TY_STRING_ENTRY", conn);
            ArrayDescriptor ad = ArrayDescriptor.createDescriptor("TY_STRING_MAP", conn);

            int i = 0;
            int len = objMap.size();
            STRUCT[] structArray = new STRUCT[len];
            for (Entry entry : objMap.entrySet())
            {
                Object[] obj = new Object[2];
                obj[0] = (String)entry.getKey();
                obj[1] = (String)entry.getValue();
                structArray[i] = new STRUCT(sd, conn, obj);
                i++;
            }
            array = new ARRAY(ad, conn, structArray);
        }
        catch (Exception e)
        {
            throw new ApplicationException("转换数据库ARRAY对象失败！", e);
        }
        return array;
    }
}