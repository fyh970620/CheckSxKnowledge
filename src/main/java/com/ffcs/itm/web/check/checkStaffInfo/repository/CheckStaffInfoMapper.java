package com.ffcs.itm.web.check.checkStaffInfo.repository;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.checkStaffInfo.entity.CheckStaffInfo;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface CheckStaffInfoMapper {

    public List<StaffUser> selectListSx();

    public boolean addOneStaffInfo(@Param("csi") CheckStaffInfo csi);

    public Integer selectExist(@Param("staffId")Integer staffId);

    public CheckStaffInfo selectMySelfMessage(@Param("staffId")Integer staffId);

    public boolean delOneMessage(@Param("staffId")Integer staffId);

    public boolean updateOneMessage(@Param("csi")CheckStaffInfo csi);

    public List<CheckStaffInfo> selectAllstaffInfoList();
}
