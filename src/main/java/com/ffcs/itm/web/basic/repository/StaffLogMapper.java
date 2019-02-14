package com.ffcs.itm.web.basic.repository;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.entity.dto.LogModuleInfo;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface StaffLogMapper {

    /**
     * 添加系统日志
     */
    void addSysLog(@Param("staff") Staff staff, @Param("logModuleInfo") LogModuleInfo logModuleInfo);

    /**
     * 更改员工为在线状态
     */
    void updateStaffInLoginState(Staff staff);

    /**
     * 记录登录 session 信息
     */
    void addUserSession(@Param("staffId") Long staffId, @Param("sessionId") String sessionId);
}
