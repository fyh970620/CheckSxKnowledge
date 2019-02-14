package com.ffcs.itm.web.outtersso.support;

import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.bean.common.MD5Encode;
import com.bsnnms.bean.user.StaffInfo;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;
import com.ffcs.itm.web.basic.entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XJUserSecurity {

    private static final String SQL_STAFF_MSG_BY_USERNAME = "SELECT STAFF_ID,PASSWD,STAFF_NAME,STATE,REGION_ID,USER_NAME,ORG_ID ,PRI_LEVEL FROM STAFF WHERE USER_NAME = ?";
    private static final String SQL_STAFF_MSG_BY_OAUSERNAME = "SELECT ITSM_ACCOUT FROM CUST_HLJ_OA_BIND_ITSM WHERE OA_ACCOUT=?";
    private static String GS_GET_OA_MAP_USERNAME = "SELECT C.ITSM_USER_NAME FROM CUST_GS_USER_MAP_OA C WHERE C.OA_USER_NAME = ?";

    private static String ND_AUTO_LOGIN = "SELECT A.STAFF_ID,A.PASSWD,A.STAFF_NAME,A.STATE,A.REGION_ID,A.USER_NAME,A.ORG_ID ,A.PRI_LEVEL FROM STAFF A,STAFF_DOMAIN_RELATION B"
            + " WHERE A.STAFF_ID=B.STAFF_ID AND LOWER(B.DOMAIN_NO)=(select trim(lower(domain_no||?)) domain_no from domain_config c where c.domain_config_id=?)";


    public XJUserSecurity() {

    }

    /**
     * 验证OA单点登录
     * 
     * @param OAUserName
     * @return
     * @throws SystemException
     * @throws ApplicationException
     */
    public static String getUserName(String OAUserName) throws SystemException, ApplicationException {

        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try {
            ptmt = conn.prepareStatement(SQL_STAFF_MSG_BY_OAUSERNAME);
            ptmt.setString(1, OAUserName);
            rs = ptmt.executeQuery();
            String itsmAccout = "";
            if (rs != null && rs.next()) {
                itsmAccout = rs.getString(1);
            }
            return itsmAccout;
        } catch (SQLException e) {
            throw new SystemException("380018", e);
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }

    public static String gsGetOaMapUsername(String OAUserName) throws SystemException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        try {
            ptmt = conn.prepareStatement(GS_GET_OA_MAP_USERNAME);
            ptmt.setString(1, OAUserName);
            rs = ptmt.executeQuery();
            String itsmAccout = "";
            if (rs.next()) {
                itsmAccout = rs.getString(1);
            }
            return itsmAccout;
        } catch (SQLException e) {
            throw new SystemException("380018", e);
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }

    public static StaffInfo check(String loginName, String inPassword) throws SystemException, ApplicationException {

        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String isForceLogin = "-1";
        try {
            ptmt = conn.prepareStatement(SQL_STAFF_MSG_BY_USERNAME);
            ptmt.setString(1, loginName);
            rs = ptmt.executeQuery();
            // 读取员工基本信息
            if (rs.next()) {
                int staffId = rs.getInt(1);
                String staffPassword = rs.getString(2);
                String staffName = rs.getString(3);
                String staffState = rs.getString(4);

                // inPassword = MD5Encode.encode(staffId + inPassword);
                inPassword = "".equals(inPassword) ? "" : MD5Encode.encode(inPassword);
                if (!"".equals(inPassword) && !inPassword.equals(staffPassword.toUpperCase())) {
                    // throw new SystemException("密码错误");
                    throw new ApplicationException("密码错误!");
                }

                // 员工已经锁定
                if (staffState.equals("2VL")) {
                    // throw new SystemException("380013");
                    throw new ApplicationException("该员工已经锁定!");
                }
                // 员工已经停用
                if (staffState.equals("2VX")) {
                    // throw new SystemException("380014");
                    throw new ApplicationException("该员工已经停用!");
                }
                // 员工已经登陆,重复登陆
                /*if (staffState.equals("2VI") && isForceLogin.equals("0")) {
                    // throw new SystemException("380016");
                    throw new ApplicationException("该员工已经登录!");
                }*/
                // 登陆成功或强制登陆成功(2015.5.5  REQUEST_20150413_104090_出差的员工也能正常登录)
                if (staffState.equals("2VA") || (staffState.equals("2VI") && isForceLogin.equals("-1"))
                        || staffState.equals("2VE")) {
                    StaffInfo info = new StaffInfo();
                    // 登陆成功设置员工代码和员工姓名,员工IP和登陆ID有servlet取得
                    info.setStaffId(staffId);
                    info.setStaffName(staffName);
                    info.setRegionId(rs.getInt(5));
                    info.setLoginName(rs.getString(6));
                    // info.setPswd(inPassword);
                    info.setOrgId(rs.getInt(7));
                    info.setPriLevel(rs.getString(8));
                    info.setPswd(staffPassword);
                    return info;
                }
                // 员工状态错误
                // throw new SystemException("380017");
                throw new ApplicationException("员工状态错误!");
            } else {
                // 数据库内无该员工
                // throw new SystemException("380012");
                throw new ApplicationException("数据库内无该员工!");
            }
        } catch (SQLException e) {
            throw new SystemException("380018", e);
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }


    public static Staff checkStaff(String loginName, String inPassword) throws SystemException, ApplicationException {

        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String isForceLogin = "-1";
        try {
            ptmt = conn.prepareStatement(SQL_STAFF_MSG_BY_USERNAME);
            ptmt.setString(1, loginName);
            rs = ptmt.executeQuery();
            // 读取员工基本信息
            if (rs.next()) {
                int staffId = rs.getInt(1);
                String staffPassword = rs.getString(2);
                String staffName = rs.getString(3);
                String staffState = rs.getString(4);

                // inPassword = MD5Encode.encode(staffId + inPassword);
                inPassword = "".equals(inPassword) ? "" : MD5Encode.encode(inPassword);
                if (!"".equals(inPassword) && !inPassword.equals(staffPassword.toUpperCase())) {
                    // throw new SystemException("密码错误");
                    throw new ApplicationException("密码错误!");
                }

                // 员工已经锁定
                if (staffState.equals("2VL")) {
                    // throw new SystemException("380013");
                    throw new ApplicationException("该员工已经锁定!");
                }
                // 员工已经停用
                if (staffState.equals("2VX")) {
                    // throw new SystemException("380014");
                    throw new ApplicationException("该员工已经停用!");
                }
                // 员工已经登陆,重复登陆
                if (staffState.equals("2VI") && isForceLogin.equals("0")) {
                    // throw new SystemException("380016");
                    throw new ApplicationException("该员工已经登录!");
                }
                // 登陆成功或强制登陆成功(2015.5.5  REQUEST_20150413_104090_出差的员工也能正常登录)
                if (staffState.equals("2VA") || (staffState.equals("2VI") && isForceLogin.equals("-1"))
                        || staffState.equals("2VE")) {
                    Staff staff = new Staff();
                    // 登陆成功设置员工代码和员工姓名,员工IP和登陆ID有servlet取得
                    staff.setId(Long.valueOf(staffId));
                    staff.setUserName(rs.getString(6));
                    staff.setStaffName(staffName);
                    staff.setRegionId(rs.getInt(5));
                    staff.setPriLevel(rs.getString(8));
                    staff.setPasswd(staffPassword);
                    return staff;
                }
                // 员工状态错误
                // throw new SystemException("380017");
                throw new ApplicationException("员工状态错误!");
            } else {
                // 数据库内无该员工
                // throw new SystemException("380012");
                throw new ApplicationException("数据库内无该员工!");
            }
        } catch (SQLException e) {
            throw new SystemException("380018", e);
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }

    /**
     * windows主机域账号绑定自动登录系统
     * @param loginName
     * @param inPassword
     * @return
     * @throws SystemException
     * @throws ApplicationException
     */
    public static StaffInfo ndAutoLogin(String loginName, String inPassword)
            throws SystemException, ApplicationException {

        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        String isForceLogin = "-1";
        try {
            ptmt = conn.prepareStatement(ND_AUTO_LOGIN);

            int last = loginName.lastIndexOf("\\");
            String domainConfigId = "1";
            if (last > 0) {
                domainConfigId = loginName.substring(0, last);
                loginName = loginName.substring(last + 1, loginName.length());
            }
            ptmt.setString(1, loginName);
            ptmt.setString(2, domainConfigId);
            rs = ptmt.executeQuery();

            // 读取员工基本信息
            if (rs.next()) {
                int staffId = rs.getInt(1);
                String staffPassword = rs.getString(2);
                String staffName = rs.getString(3);
                String staffState = rs.getString(4);

                // inPassword = MD5Encode.encode(staffId + inPassword);
                inPassword = "".equals(inPassword) ? "" : MD5Encode.encode(inPassword);
                if (!"".equals(inPassword) && !inPassword.equals(staffPassword.toUpperCase())) {
                    throw new ApplicationException("密码错误!");
                }

                // 员工已经锁定
                if (staffState.equals("2VL")) {
                    throw new ApplicationException("该员工已经锁定!");
                }
                // 员工已经停用
                if (staffState.equals("2VX")) {
                    throw new ApplicationException("该员工已经停用!");
                }
                // 员工已经登陆,重复登陆
                if (staffState.equals("2VI") && isForceLogin.equals("0")) {
                    throw new ApplicationException("该员工已经登录!");
                }
                // 登陆成功或强制登陆成功
                if (staffState.equals("2VA") || (staffState.equals("2VI") && isForceLogin.equals("-1"))) {
                    StaffInfo info = new StaffInfo();
                    // 登陆成功设置员工代码和员工姓名,员工IP和登陆ID有servlet取得
                    info.setStaffId(staffId);
                    info.setStaffName(staffName);
                    info.setRegionId(rs.getInt(5));
                    info.setLoginName(rs.getString(6));
                    // info.setPswd(inPassword);
                    info.setOrgId(rs.getInt(7));
                    info.setPriLevel(rs.getString(8));
                    info.setPswd(staffPassword);
                    return info;
                }
                // 员工状态错误
                throw new ApplicationException("员工状态错误!");
            } else {
                // 数据库内无该员工
                throw new ApplicationException("数据库内无该员工!");
            }
        } catch (SQLException e) {
            throw new SystemException("380018", e);
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }

    /**
     * 去掉byte转string类型时string的不明字符串
     * @param s
     * @return
     */
    public static String filterCharInString(String s) {
        int i, len = s.length();
        StringBuffer sb = new StringBuffer(len);
        char c;
        for (i = 0; i <= len - 1; i++) {
            c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            } else {
                if (c == '-') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }


    /**
    * 
    * @param oMobile
    * @return
    * @throws SystemException
    * @throws ApplicationException
    * xuhl
    * Sep 11, 2014 2:59:23 PM
    */
    public static ArrayList scItsmAutoLoginUser(String oMobile) throws SystemException, ApplicationException {

        Connection conn = DBCtrl.getConnection();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        ArrayList staffInfos = new ArrayList();
        try {
            ptmt = conn.prepareStatement(
                    "select t.STAFF_ID,PASSWD,STAFF_NAME,STATE,REGION_ID,USER_NAME,ORG_ID ,PRI_LEVEL,rownum from staff t,staff_contact d where t.staff_id = d.staff_id and t.state not in ('2VL','2VX') and d.mobile=? and rownum<=2");
            ptmt.setString(1, oMobile);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                StaffInfo info = new StaffInfo();
                info.setStaffId(rs.getInt(1));
                info.setStaffName(rs.getString(3));
                info.setRegionId(rs.getInt(5));
                info.setLoginName(rs.getString(6));
                info.setOrgId(rs.getInt(7));
                info.setPriLevel(rs.getString(8));
                info.setPswd(rs.getString(2));
                staffInfos.add(info);
            }
            return staffInfos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("根据手机号查询相关用户出现错误");
        } finally {
            DBCtrl.close(conn, ptmt, rs);
        }
    }
}
