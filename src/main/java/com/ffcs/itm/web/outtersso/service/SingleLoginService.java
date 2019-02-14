package com.ffcs.itm.web.outtersso.service;

import com.bsnnms.bean.common.AESSecurity;
import com.bsnnms.bean.common.propertyCfg.PropertyConfig;
import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.web.basic.service.StaffService;
import com.ffcs.itm.web.common.service.SysConfigService;
import com.ffcs.itm.web.common.utils.date.DateUtil;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.service.StaffLogService;
import com.ffcs.itm.web.basic.support.CheckPasswd;
import com.ffcs.itm.web.outtersso.support.SessionMgr_WorkFlow;
import com.ffcs.itm.web.outtersso.support.XJUserSecurity;
import com.ffcs.itm.web.support.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Service
public class SingleLoginService {
    @Autowired
    private StaffLogService staffLogService;

    @Autowired
    private StaffService staffService;

    org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(SingleLoginService.class);


    private String sourceId;
    private String userName;
    private String key;
    private String config;
    private String url;
    private String params;
    private String noKey;

    private String noCheck; //用于忽略密码策略
    public static final int LIMIT_SECOND = 600000;


    public void single(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        init(request, response);
        HttpSession session = request.getSession();
        boolean isCheckPwd = true;
        if (!isExistsSession(session)) {
            if (!isCheck())
                return;
            try {

                Staff staff = staffService.checkStaff(userName);//XJUserSecurity.checkStaff(userName, "");
                staff.setLoginId(session.getId());
                staff.setLoginIp(request.getRemoteAddr());
                // 写入 session
                SessionInfo.setStaff(staff);
                session.setAttribute("staff", staff);
                SessionMgr_WorkFlow.logIn(session, Integer.parseInt(staff.getId().toString()));
                staffLogService.addLoginLog(staff, session.getId());
                CheckPasswd cPwd = new CheckPasswd();
                if (noCheck == null || !"Y".equals(noCheck)) {
                    isCheckPwd = cPwd.checkPasswdAfterLogin(staff.getId().intValue(), staff.getEncodePasswd());
                    if (isCheckPwd) {
                        isCheckPwd = cPwd.checkTimeForDue();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException(e.getMessage());
            }
        }
        try {
            String urlArgs = getParams();
            if (!"".equals(urlArgs)) {
                url = url + "?" + urlArgs;
            }
            session.setAttribute("url", url);
            response.addHeader("P3P", "CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
            if (isCheckPwd) {
                response.sendRedirect(url);
            } else {
                response.sendRedirect("/modify");
            }
        } catch (IOException e) {
            throw new ApplicationException(e.getMessage());
        }

    }

    public void init(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        sourceId = (null == request.getParameter("sourceid")) ? "001" : request.getParameter("sourceid");
        userName = (null == request.getParameter("username")) ? "SC901" : request.getParameter("username");
        key = request.getParameter("key");
        params = request.getParameter("params");
        config = request.getParameter("config");
        noKey = request.getParameter("noKey");
        noCheck = request.getParameter("noCheck");
        if (config != null) {
            url = PropertyConfig.getValueByCode(config);
        }
    }

    public boolean isExistsSession(HttpSession session) {
        Staff staff = (Staff) session.getAttribute("staff");
        if (staff != null && staff.getStaffName().equals(userName)) {
            return true;
        }
        return false;
    }

    /**
     * 校验key是否过期
     * @return
     * @throws ApplicationException
     */
    public boolean isCheck() throws ApplicationException {
        String keyTime;
        try {

            if ("Y".equals(noKey)) {
                return true;
            }

            if (key != null && !"".equals(key)) {
                keyTime = AESSecurity.decode(key, "FFCS_ITM");
                Date nowDate = new Date();
                Date keyDate = DateUtil.strToDateTime(keyTime);
                if (Math.abs((keyDate.getTime() - nowDate.getTime())) < LIMIT_SECOND) {
                    return true;
                } else {
                    throw new ApplicationException("KEY失效：" + keyTime);
                }

            } else {
                throw new ApplicationException("KEY不能为空");
            }
        } catch (ApplicationException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    // 解析url中参数键值对 add by zhangye 13-08-15
    public String getParams() {
        String urlParam = "";
        if (params != null) {
            String paramsStr = params.substring(1, params.length() - 1);
            String paramsStrArr[] = paramsStr.split(",");

            for (int i = 0; i < paramsStrArr.length; i++) {
                if ("".equals(urlParam)) {
                    urlParam = paramsStrArr[i].replaceAll(":", "=");
                } else {
                    urlParam = urlParam + "&" + paramsStrArr[i].replaceAll(":", "=");
                }
            }
        }
        return urlParam;
    }
}
