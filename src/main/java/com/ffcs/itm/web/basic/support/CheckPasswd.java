package com.ffcs.itm.web.basic.support;


import com.bsnnms.bean.common.ContextInfo;
import com.bsnnms.bean.common.DBCtrl;
import com.bsnnms.bean.common.MD5Encode;
import com.bsnnms.exception.ApplicationException;
import com.bsnnms.exception.SystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CheckPasswd {
    private static final String CHART_TEMPLATE_ASC = "qwertyuiopasdfghjklzxcvbnm";

    private static final String CHART_TEMPLATE_DESC = "mnbvcxzlkjhgfdsapoiuytrewq";

    private static final String NUMBER_CHART_TEMPLATE_ASC = "1234567890";

    private static final String NUMBER_CHART_TEMPLATE_DESC = "0987654321";

    private static final String SQL_GET_PASSWD_TIME   = "SELECT DECODE(MAX(CHANGE_DATE),NULL,?,TRUNC(SYSDATE) - TRUNC(MAX(CHANGE_DATE))) FROM PASSWD_HISTORY WHERE STAFF_ID = ?";

    private static final String SQL_GET_PASSWD_TIME_STYLE = "SELECT DECODE(MAX(CHANGE_DATE),NULL,NULL,?,?,TRUNC(SYSDATE) - TRUNC(MAX(CHANGE_DATE))) PWDEXIT FROM PASSWD_HISTORY T WHERE STAFF_ID = ?";

    private static final String SQL_GET_HISTORY_COUNT = "SELECT COUNT(PASSWD_HISTORY_ID) FROM PASSWD_HISTORY WHERE STAFF_ID = ?";

    private static final String SQL_ADD_HISTORY       = "INSERT INTO PASSWD_HISTORY(PASSWD_HISTORY_ID,STAFF_ID,PASSWD,CHANGE_DATE) VALUES (PASSWD_HISTORY_ID_SEQ.NEXTVAL,?,?,SYSDATE)";

    private static final String SQL_DEL_HISTORY       = "DELETE FROM PASSWD_HISTORY WHERE PASSWD_HISTORY_ID IN (SELECT PASSWD_HISTORY_ID FROM "
            + "(SELECT PASSWD_HISTORY_ID FROM PASSWD_HISTORY WHERE STAFF_ID = ? ORDER BY CHANGE_DATE) WHERE ROWNUM <= ?)";

    private static final String SQL_UPDATE_HISTORY    = "UPDATE PASSWD_HISTORY SET PASSWD = ?, CHANGE_DATE = SYSDATE WHERE STAFF_ID = ? AND CHANGE_DATE = "
            + "(SELECT MIN(CHANGE_DATE) FROM PASSWD_HISTORY WHERE STAFF_ID = ?)";

    private static final String SQL_REPEAT_ON_HISTORY = "SELECT * FROM PASSWD_HISTORY WHERE STAFF_ID = ? AND PASSWD = ?";

    private static final String SQL_GET_USERNAME_BY_ID = "SELECT USER_NAME, STAFF_NAME FROM STAFF WHERE STAFF_ID = ?";

    private static final String SQL_AFTER_LOCKED_TIME = "SELECT TRUNC((SYSDATE - STATE_DATE) * 24 * 60) Minutes FROM STAFF WHERE STAFF_ID = ?";

    //查询是否存在
    private static final String SQL_RESET_PASSWORD_CONFIG = "SELECT COUNT(STAFF_ID) AS COUNT FROM reset_password_config WHERE STAFF_ID = ?";

    //增加当前记录 如果没有增加一条记录
    private static final String SQL_INSERT_RPASSWORD_CONFIG = "INSERT INTO RESET_PASSWORD_CONFIG(STAFF_ID,EXECUTE_TIMES,CREATE_DATE) VALUES(?,0,SYSDATE)";

    //更新执行次数
    private static final String SQL_UPDATE_EXECUTE_TIMES = "UPDATE RESET_PASSWORD_CONFIG SET EXECUTE_TIMES = EXECUTE_TIMES + 1,CREATE_DATE = SYSDATE WHERE STAFF_ID = ?";

    //获取执行次数
    private static final String SQL_GET_EXECUTE_TIMES = "SELECT EXECUTE_TIMES AS TIMES FROM RESET_PASSWORD_CONFIG WHERE STAFF_ID = ?";

    //执行次数清零
    private static final String SQL_SET_ZERO_EXECUTE_TIMES = "UPDATE RESET_PASSWORD_CONFIG SET EXECUTE_TIMES = 0, CREATE_DATE = SYSDATE WHERE STAFF_ID = ?";

    //判断是否超过一天
    private static final String SQL_IS_OVER_DATE = "SELECT TRUNC(SYSDATE)-TRUNC((SELECT CREATE_DATE AS C_DATE FROM RESET_PASSWORD_CONFIG WHERE STAFF_ID = ?)) AS DAYS FROM DUAL";

    private static final String KEY_VALIDITY          = "PASSWD_VALIDITY_PERIOD";

    private static final String KEY_INIT              = "PASSWD_INIT";

    private static final String KEY_MINLENGTH         = "PASSWD_MIN_LENGTH";

    private static final String KEY_MAXLENGTH         = "PASSWD_MAX_LENGTH";

    private static final String KEY_HISTORY           = "PASSWD_HISTORY";

    private static final String KEY_COMPLEX           = "PASSWD_COMPLEX";

    private static final String KEY_REPEAT            = "PASSWD_REPEAT";

    private static final String KEY_ERROR_COUNT       = "PASSWD_MAX_ERROR_COUNT";

    private static final String KEY_LOCKED_TIME       = "PASSWD_LOCKED_TIME";

    private static final String KEY_BEFORE_VALIDITY		  = "PASSWD_DUE_TIP";

    private static final String KEY_VALID_CODE		  = "PASSWD_VALID_CODE";

    private static final String KEY_KEYBOARD_SEQUENCE = "PASSWORD_KEYBOARD_SEQUENCE";

    private static final String KEY_IS_CONTAINS_USERNAME = "PASSWORD_CONTAINS_USERNAME";

    private int                 staffId;

    private String 				userName;

    private String              pswd;

    private String              encodePswd;

    private String              checkMsg;

    private boolean             initError = false;

    private boolean             periodError = false;

    private StrategyConfig      strategyCfg           = new StrategyConfig();

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setEncodePswd(String encodePswd) {
        this.encodePswd = encodePswd;
    }

    public String getCheckMsg() {
        return checkMsg;
    }

    public void setCheckMsg(String checkMsg) {
        this.checkMsg = checkMsg;
    }

    public String getEncodePswd() {
        return this.encodePswd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isInitError() {
        return initError;
    }

    public void setInitError(boolean initError) {
        this.initError = initError;
    }

    public boolean isPeriodError() {
        return periodError;
    }

    public void setPeriodError(boolean periodError) {
        this.periodError = periodError;
    }

    public boolean overTimes(int staffId) throws SystemException, ApplicationException {
        boolean isCheck = true;
        //strategyCfg.setKey(KEY_VALIDITY);
        //	int beforeValidityTime = strategyCfg.getValueByInt();
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_GET_PASSWD_TIME_STYLE);
            pstmt.setInt(1, staffId);
            //pstmt.setInt(2, ValidityTime);
            // pstmt.setInt(3, this.staffId);
            rs = pstmt.executeQuery();

        }
        catch (SQLException e) {
            throw new ApplicationException("判断输错验证码次数错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        return isCheck;
    }

    public boolean hasLocked(int staffId) throws ApplicationException, SystemException {
        boolean isLocked = true;
        this.staffId = staffId;
        strategyCfg.setKey(KEY_LOCKED_TIME);
        if (strategyCfg.hasStrategy()) {
            int lockedTime = strategyCfg.getValueByInt();
            Connection conn = DBCtrl.getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = conn.prepareStatement(SQL_AFTER_LOCKED_TIME);
                pstmt.setInt(1, this.staffId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    int minutes = rs.getInt(1);
                    if (minutes >= lockedTime) {
                        isLocked = false;
                        StaffCtrl.setStaffState(Integer.toString(this.staffId), "2VA", "1");
                    }
                }
            }
            catch (SQLException e) {
                throw new ApplicationException("判断密码有效时间错误!", e);
            }
            finally {
                DBCtrl.close(conn, pstmt, rs);
            }
        }
        return isLocked;
    }

    public boolean checkPasswdAfterLogin(int staffId, String encodePswd, String password)
            throws ApplicationException, SystemException {
        this.setStaffId(staffId);
        this.setEncodePswd(encodePswd);
        Map names = null;
        try {
            names = getNamesById(staffId);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean isCheck = this.checkInitPasswd() && this.checkTime()
                && this.checkPwdIsContainsName(names, password)
                && checkIsKeyboardSequence(password) && checkComplex(password)
                && checkMinLength(password);
        return isCheck;
    }

    public boolean checkPasswdAfterLogin(int staffId, String encodePswd)
            throws ApplicationException, SystemException {
        this.setStaffId(staffId);
        this.setEncodePswd(encodePswd);
        boolean isCheck = this.checkInitPasswd() && this.checkTime();
        return isCheck;
    }

    public boolean checkPasswdAfterChange(int staffId, String pswd)
            throws SystemException, ApplicationException {
        Map names = null;
        try {
            names = getNamesById(staffId);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setStaffId(staffId);
        this.setPswd(pswd);
        this.setEncodePswd(MD5Encode.encode(this.staffId + this.pswd));
        boolean isCheck = this.checkMinLength(null) && this.checkMaxLength()
                && this.checkInitPasswd() && this.checkComplex(null)
                && this.checkRepeat() && checkIsKeyboardSequence(pswd)
                && this.checkPwdIsContainsName(names, pswd);
        if (isCheck) {
            this.savePswdHistroy();
        }
        return isCheck;
    }

    public boolean checkPasswdAfterChange(int staffId, String pswd, ValidCodeHolder validCodeHolder)
            throws SystemException, ApplicationException {
        this.setStaffId(staffId);
        this.setPswd(pswd);
        Map names = null;
        try {
            names = getNamesById(staffId);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setEncodePswd(MD5Encode.encode(this.staffId + this.pswd));
        boolean isCheck = this.checkValidCode(validCodeHolder)
                && this.checkMinLength(null) && this.checkMaxLength()
                && this.checkInitPasswd() && this.checkComplex(null)
                && this.checkRepeat() && checkIsKeyboardSequence(pswd)
                && this.checkPwdIsContainsName(names, pswd);
        if (isCheck) {
            this.savePswdHistroy();
        }
        return isCheck;
    }

    public boolean isOverrunErrorCount(int iCurrentCount)
            throws ApplicationException, SystemException {
        boolean isOver = false;
        strategyCfg.setKey(KEY_ERROR_COUNT);
        if (strategyCfg.hasStrategy()) {
            int maxCount = strategyCfg.getValueByInt();
            if (iCurrentCount >= maxCount) {
                isOver = true;
            }
        }
        return isOver;
    }

    private boolean checkInitPasswd() throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_INIT);
        if (strategyCfg.hasStrategy()) {
            String initPasswd = MD5Encode.encode(this.staffId
                    + ContextInfo.STAFF_INIT_PASSWD);
            if (this.encodePswd.equals(initPasswd)) {
                isCheck = false;
                this.setCheckMsg("当前密码与初始密码相同,请修改!");
                this.setInitError(true);
            }
        }
        return isCheck;
    }
    public boolean checkTimeForDue() throws SystemException, ApplicationException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_VALIDITY);

        if (strategyCfg.hasStrategy()) {

            int validityTime = strategyCfg.getValueByInt();
            strategyCfg.setKey(KEY_BEFORE_VALIDITY);

            if (strategyCfg.hasStrategy()) {

                int beforeValidityTime = strategyCfg.getValueByInt();
                Connection conn = DBCtrl.getConnection();
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                try {
                    pstmt = conn.prepareStatement(SQL_GET_PASSWD_TIME_STYLE);
                    pstmt.setInt(1, beforeValidityTime);
                    pstmt.setInt(2, validityTime);
                    pstmt.setInt(3, this.staffId);
                    rs = pstmt.executeQuery();
                    if (rs.next() && validityTime - rs.getInt(1) <= beforeValidityTime) {
                        isCheck = false;
                        this.setCheckMsg("当前密码到期时间少于" + beforeValidityTime + "天，请尽快修改!");
                    }
                }
                catch (SQLException e) {
                    throw new ApplicationException("判断密码有效时间错误!", e);
                }
                finally {
                    DBCtrl.close(conn, pstmt, rs);
                }
            }

        }
        return isCheck;
    }
    private boolean checkTime() throws SystemException, ApplicationException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_VALIDITY);
        if (strategyCfg.hasStrategy()) {
            int validityTime = strategyCfg.getValueByInt();
            Connection conn = DBCtrl.getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = conn.prepareStatement(SQL_GET_PASSWD_TIME);
                pstmt.setInt(1, validityTime);
                pstmt.setInt(2, this.staffId);
                rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) >= validityTime) {
                    isCheck = false;
                    this.setCheckMsg("当前密码已经过期,请修改!");
                    this.setPeriodError(true);
                }
            }
            catch (SQLException e) {
                throw new ApplicationException("判断密码有效时间错误!", e);
            }
            finally {
                DBCtrl.close(conn, pstmt, rs);
            }
        }
        return isCheck;
    }

    private boolean checkMinLength(String password) throws ApplicationException, SystemException {
        password = password == null ? this.pswd : password;
        boolean isCheck = true;
        strategyCfg.setKey(KEY_MINLENGTH);
        if (strategyCfg.hasStrategy()) {
            int minLength = strategyCfg.getValueByInt();
            if (password.length() < minLength) {
                isCheck = false;
                this.setCheckMsg("密码的长度不能短于" + minLength);
            }
        }
        return isCheck;
    }

    private boolean checkPwdIsContainsName(Map names, String password) throws ApplicationException, SystemException {
        boolean isCheck = true;
        String staffName = (String) names.get("STAFF_NAME");
        String userName = (String) names.get("USER_NAME");
        strategyCfg.setKey(KEY_IS_CONTAINS_USERNAME);
        if (strategyCfg.hasStrategy()) {
            userName = userName.toLowerCase();
            staffName = staffName.toLowerCase();
            password = password.toLowerCase();
            if (password.contains(userName) || password.contains(staffName)) {
                isCheck = false;
                this.setCheckMsg("密码不能包含用户名或员工名,请修改!");
            }
        }
        return isCheck;
    }

    private boolean checkIsKeyboardSequence(String password) throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_KEYBOARD_SEQUENCE);
        if (strategyCfg.hasStrategy()) {
            int sequenceLength = strategyCfg.getValue() == null ? 5 : strategyCfg.getValueByInt();
            password = password.toLowerCase();
            if (isKeyboardSequence(password, sequenceLength)) {
                isCheck = false;
                this.setCheckMsg("密码中不能包含键盘顺序的字符");
            }
        }
        return isCheck;
    }

    private boolean isKeyboardSequence(String str, int sequenceLength) {
        boolean result = false;
        String subStr = "";
        for (int i = 0; i < (str.length() - (sequenceLength - 1)); i++) {
            subStr = str.substring(i, i + sequenceLength);
            if (CHART_TEMPLATE_ASC.contains(subStr)) {
                result = true;
                break;
            }
            if (CHART_TEMPLATE_DESC.contains(subStr)) {
                result = true;
                break;
            }
            if (NUMBER_CHART_TEMPLATE_ASC.contains(subStr)) {
                result = true;
                break;
            }
            if (NUMBER_CHART_TEMPLATE_DESC.contains(subStr)) {
                result = true;
                break;
            }
        }

        return result;
    }

    private boolean checkMaxLength() throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_MAXLENGTH);
        if (strategyCfg.hasStrategy()) {
            int maxLength = strategyCfg.getValueByInt();
            if (this.pswd.length() > maxLength) {
                isCheck = false;
                this.setCheckMsg("密码的长度不能长于" + maxLength);
            }
        }
        return isCheck;
    }

    private boolean checkComplex(String psd) throws ApplicationException, SystemException {
        String password = psd == null ? this.pswd : psd;
        boolean isCheck = true;
        strategyCfg.setKey(KEY_COMPLEX);
        if (strategyCfg.hasStrategy()) {
            // 复杂度, 密码必须含数字、大写字母、小写字母、特殊字符中4项中的 x 项
            String x = strategyCfg.getValue();
            int complexRate = 2;
            if (x != null && Pattern.matches("\\d+", x)) {
                complexRate = Integer.parseInt(x);
                if (complexRate < 2) {
                    complexRate = 2;
                }
                else if (complexRate > 4) {
                    complexRate = 4;
                }
            }

            // 计算密码复杂度
            int actualComplex = 0;
            int i = 0;
            String[] regexs = new String[]{".*\\d.*", ".*[a-z].*", ".*[A-Z].*", ".*[^A-Za-z0-9].*"};
            while (actualComplex < complexRate && i < 4) {
                if (Pattern.matches(regexs[i], password)) {
                    actualComplex++;
                }
                i++;
            }

            if (actualComplex < complexRate) {
                isCheck = false;
                String msg;
                if (complexRate == 4) {
                    msg = "密码必须含数字、大写字母、小写字母、特殊字符4项";
                }
                else {
                    msg = "密码必须含数字、大写字母、小写字母、特殊字符4项中的" + complexRate + "项";
                }
                this.setCheckMsg(msg);
            }

        }
        return isCheck;
    }

    private boolean checkRepeat() throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_REPEAT);
        if (strategyCfg.hasStrategy()) {
            Connection conn = DBCtrl.getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = conn.prepareStatement(SQL_REPEAT_ON_HISTORY);
                pstmt.setInt(1, this.staffId);
                pstmt.setString(2, this.encodePswd);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    isCheck = false;
                    this.setCheckMsg("密码与历史记录中的相同");
                }
            }
            catch (SQLException e) {
                throw new ApplicationException("检测密码同历史记录是否重复错误!", e);
            }
            finally {
                DBCtrl.close(conn, pstmt, rs);
            }
        }
        return isCheck;
    }

    private boolean checkValidCode(ValidCodeHolder validCodeHolder) throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_VALID_CODE);
        if (strategyCfg.hasStrategy()) {
            String limitTime = strategyCfg.getValue();
            if (validCodeHolder.isWrongCode()) {
                isCheck = false;
                this.setCheckMsg("短信口令有误");
            }
            else if (validCodeHolder.isTimeout(limitTime)) {
                isCheck = false;
                this.setCheckMsg("短信口令已失效");
            }
        }

        return isCheck;
    }

    /**
     * 验证短信验证码的有效性
     * @param validCodeHolder
     * @return
     * @throws ApplicationException
     * @throws SystemException
     */

    public boolean checkVerifyCode(ValidCodeHolder validCodeHolder) throws ApplicationException, SystemException {
        boolean isCheck = true;
        strategyCfg.setKey(KEY_VALID_CODE);
        //WeakHashMap<String, Integer> map = new WeakHashMap<String, Integer>();
        String limitTime = strategyCfg.getValue();
        if (validCodeHolder.isWrongCode()) {
            isCheck = false;
        }
        else if (validCodeHolder.isTimeout(limitTime)) {
            isCheck = false;
        }

        return isCheck;
    }

    public boolean isExistConfig(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        try {
            pstmt = conn.prepareStatement(SQL_RESET_PASSWORD_CONFIG);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                isExist = true;
            }
        }
        catch (Exception e) {
            throw new ApplicationException("查询执行表发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        return isExist;
    }

    public void setExecuteTimes(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SET_ZERO_EXECUTE_TIMES);
            pstmt.setInt(1, staffId);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            throw new ApplicationException("清零执行次数发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
    }

    private Map getNamesById(int staffId) throws SystemException, SQLException, ApplicationException {
        Map names = new HashMap();
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userName = null;
        String staffName = null;
        try {
            pstmt = conn.prepareStatement(SQL_GET_USERNAME_BY_ID);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            rs.next();
            userName = rs.getString(1);
            staffName = rs.getString(2);
        }
        catch (Exception e) {
            throw new ApplicationException("获取员工信息错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        names.put("STAFF_NAME", staffName);
        names.put("USER_NAME", userName);
        return names;
    }

    public void updateExecuteTimes(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(SQL_UPDATE_EXECUTE_TIMES);
            pstmt.setInt(1, staffId);
            pstmt.execute();
        }
        catch (Exception e) {
            throw new ApplicationException("更新执行次数发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt);
        }
    }

    public int getExecuteTimes(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int times = 0;
        try {
            pstmt = conn.prepareStatement(SQL_GET_EXECUTE_TIMES);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                times = rs.getInt(1);
            }
        }
        catch (Exception e) {
            throw new ApplicationException("获取执行次数发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        return times;
    }


    public int getDays(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int days = 0;
        try {
            pstmt = conn.prepareStatement(SQL_IS_OVER_DATE);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                days = rs.getInt(1);
            }
        }
        catch (Exception e) {
            throw new ApplicationException("获取相隔天数发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        return days;
    }

    public int getDate(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int times = 0;
        try {
            pstmt = conn.prepareStatement(SQL_GET_EXECUTE_TIMES);
            pstmt.setInt(1, staffId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                times = rs.getInt(1);
            }
        }
        catch (Exception e) {
            throw new ApplicationException("获取执行次数发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
        return times;
    }

    public void insertConfig(int staffId) throws SystemException, SQLException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_INSERT_RPASSWORD_CONFIG);
            pstmt.setInt(1, staffId);
            pstmt.execute();
        }
        catch (Exception e) {
            throw new ApplicationException("插入执行次数记录发生错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
    }

    private void savePswdHistroy() throws SystemException, ApplicationException {
        Connection conn = DBCtrl.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            strategyCfg.setKey(KEY_HISTORY);
            if (strategyCfg.hasStrategy()) {
                int maxHistoryCount = strategyCfg.getValueByInt();
                pstmt = conn.prepareStatement(SQL_GET_HISTORY_COUNT);
                pstmt.setInt(1, this.staffId);
                rs = pstmt.executeQuery();
                rs.next();
                int currentHistoryCount = rs.getInt(1);
                if (currentHistoryCount < maxHistoryCount) {
                    pstmt = conn.prepareStatement(SQL_ADD_HISTORY);
                    pstmt.setInt(1, this.staffId);
                    pstmt.setString(2, this.encodePswd);
                    pstmt.executeUpdate();
                }
                else {
                    if (currentHistoryCount > maxHistoryCount) {
                        pstmt = conn.prepareStatement(SQL_DEL_HISTORY);
                        pstmt.setInt(1, this.staffId);
                        pstmt.setInt(2, currentHistoryCount - maxHistoryCount);
                        pstmt.executeUpdate();
                    }
                    pstmt = conn.prepareStatement(SQL_UPDATE_HISTORY);
                    pstmt.setString(1, this.encodePswd);
                    pstmt.setInt(2, this.staffId);
                    pstmt.setInt(3, this.staffId);
                    pstmt.executeUpdate();
                }
            }
            else {
                strategyCfg.setKey(KEY_VALIDITY);
                if (strategyCfg.hasStrategy()) {
                    pstmt = conn.prepareStatement(SQL_UPDATE_HISTORY);
                    pstmt.setString(1, this.encodePswd);
                    pstmt.setInt(2, this.staffId);
                    pstmt.setInt(3, this.staffId);
                    int rowCount = pstmt.executeUpdate();
                    if (rowCount == 0) {
                        pstmt = conn.prepareStatement(SQL_ADD_HISTORY);
                        pstmt.setInt(1, this.staffId);
                        pstmt.setString(2, this.encodePswd);
                        pstmt.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new ApplicationException("保存密码历史错误!", e);
        }
        finally {
            DBCtrl.close(conn, pstmt, rs);
        }
    }
}
