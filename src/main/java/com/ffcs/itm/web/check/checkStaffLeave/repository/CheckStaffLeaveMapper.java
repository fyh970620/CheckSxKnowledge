package com.ffcs.itm.web.check.checkStaffLeave.repository;

import com.ffcs.itm.web.check.checkStaffLeave.entity.CheckStaffLeave;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CheckStaffLeaveMapper {

    public List<CheckStaffLeave> selectYourCheckMap(@Param("staffId")Integer staffId);

    public boolean addOneStaffLeave(@Param("csl")CheckStaffLeave csl);

    public boolean delOneStaffLeave(@Param("leaveId")Integer leaveId);
}
