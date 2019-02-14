package com.ffcs.itm.web.outtersso.support;
/**************************************************
 * Copyright (c) 2005.
 * 文件名称: SessionMgr_WorkFlow.java
 *
 * 当前版本: 1.0
 * 作　　者: 方旭尘
 * 完成日期: 2005-9-25
 **************************************************/

import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.exception.SystemException;
import com.ffcs.itm.web.sso.support.TicketCenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMgr_WorkFlow {

    private static String gLoginStaffId = "loginStaffId";

    private static String gGateStaffId = "gateStaffId";

    private static String gVteamStaffId = "vteamStaffId";

    private static String gKXStaffId = "kxStaffId";

    private static String gMisStaffId = "misStaffId";

    private static String gJfStaffId = "jfStaffId";

    private static String gItStaffId = "itStaffId";

    private static String gDeptStaffId = "deptStaffId";

    private static String gDevStaffId = "devStaffId";

    private static String gMartStaffId = "martStaffId";

    private static String gPlaceStaffId = "placeStaffId";

    private static String gStaffName = "loginStaffName";

    private static String gNode = "loginNode";

    private static String gNodeName = "loginNodeName";

    private static String gDeptRole = "loginDeptRole";

    private static String gOrgNode = "loginOrgNode";

    private static String gOrgNodeName = "loginOrgNodeName";

    private static String gOrgRole = "loginOrgRole";

    private static String DS_NAME = "jdbc/OracleDB";

    /*
     * LogIn: 登录函数，给各个session对象初始化 返回（int）： -- 0（登录成功） -- -1（处理异常）
     */
    public static int logIn(HttpSession session, int in_staff_code) throws SystemException {
        int ret = -1;
        String v_staff_id = "";
        String v_staff_name = "";
        String v_node = "";
        String v_nodename = "";
        String v_dept_role = "";
        String v_org_node = "";
        String v_org_nodename = "";
        String v_org_role = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBCtrl.getConnection();
            pstmt = conn.prepareStatement(
                    "select t.staff_id,t.staff_name as name,t.org_id DEPT_NODE,1 DEPT_ROLE,b.org_name DEPT_NAME from staff t,organization b where t.org_id = b.org_id(+) and t.staff_id = ? and t.state <> '0SX'");
            pstmt.setInt(1, in_staff_code);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                v_staff_id = rs.getString("STAFF_ID");
                v_staff_name = rs.getString("NAME");
                v_node = rs.getString("DEPT_NODE");
                v_nodename = rs.getString("DEPT_NAME");
                v_dept_role = rs.getString("DEPT_ROLE");
                ret = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBCtrl.close(conn, pstmt, rs);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        }

        if (ret == 0) {
            session.setAttribute(gLoginStaffId, v_staff_id);
            session.setAttribute(gVteamStaffId, v_staff_id);
            session.setAttribute(gKXStaffId, v_staff_id);
            session.setAttribute(gGateStaffId, v_staff_id);
            session.setAttribute(gMisStaffId, v_staff_id);
            session.setAttribute(gJfStaffId, v_staff_id);
            session.setAttribute(gItStaffId, v_staff_id);
            session.setAttribute(gDeptStaffId, v_staff_id);
            session.setAttribute(gDevStaffId, v_staff_id);
            session.setAttribute(gMartStaffId, v_staff_id);
            session.setAttribute(gPlaceStaffId, v_staff_id);
            session.setAttribute(gStaffName, v_staff_name);
            session.setAttribute(gNode, v_node);
            session.setAttribute(gNodeName, v_nodename);
            session.setAttribute(gDeptRole, v_dept_role);
            session.setAttribute(gOrgNode, v_org_node);
            session.setAttribute(gOrgNodeName, v_org_nodename);
            session.setAttribute(gOrgRole, v_org_role);
            ret = 0;
        }

        if (TicketCenter.hasActive()) {
            TicketCenter.createTicket(session);
        }

        return ret;
    }

    public static void logOut(HttpServletRequest req, String in_staff_id) {
        HttpSession session = req.getSession(true);
        session.removeAttribute(gLoginStaffId);
        session.removeAttribute(gGateStaffId);
        session.removeAttribute(gVteamStaffId);
        session.removeAttribute(gKXStaffId);
        session.removeAttribute(gMisStaffId);
        session.removeAttribute(gJfStaffId);
        session.removeAttribute(gItStaffId);
        session.removeAttribute(gDeptStaffId);
        session.removeAttribute(gDevStaffId);
        session.removeAttribute(gMartStaffId);
        session.removeAttribute(gPlaceStaffId);
        session.removeAttribute(gStaffName);
        session.removeAttribute(gNode);
        session.removeAttribute(gDeptRole);
        session.removeAttribute(gOrgNode);
        session.removeAttribute(gOrgRole);
        session.invalidate();
    }
}
