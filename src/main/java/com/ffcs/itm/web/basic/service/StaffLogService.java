package com.ffcs.itm.web.basic.service;

import com.bsnnms.bean.systemLog.vo.LogCode;
import com.bsnnms.bean.systemLog.vo.ModuleCode;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.entity.dto.LogModuleInfo;
import com.ffcs.itm.web.basic.repository.StaffLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记录员工登陆,登出
 */
@Service
public class StaffLogService {

    @Autowired
    private StaffLogMapper staffLogMapper;

    /**
     * 添加登录日志
     */
    public void addLoginLog(Staff staff, String sessionId) {
        // 写登陆日志
        staffLogMapper.addSysLog(staff, new LogModuleInfo(ModuleCode.STAFF, LogCode.STAFF_LOGIN));

        // 更改员工状态
        staffLogMapper.updateStaffInLoginState(staff);

        // 记录session id
        //staffLogMapper.addUserSession(staff.getId(), sessionId);
    }

    /**
     * 添加登出日志
     */
    public void addLogoutLog(Staff staff) {
        staffLogMapper.addSysLog(staff, new LogModuleInfo(ModuleCode.STAFF, LogCode.STAFF_LOGOUT));
    }
}
