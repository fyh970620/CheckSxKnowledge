package com.ffcs.itm.web.check.weeklyList.repository;

import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyCollect;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WeeklyCollectMapper {

    public List<CheckWeeklyCollect> getMyCollects(@Param("staffId") Integer staffId);
    public StaffUser selectStaffname(@Param("staffId") Integer staffId);
    public CheckWeeklyList selectWeekListName(@Param("weeklyId") Integer weeklyId);
    public boolean AddOneCollect(@Param("cwc") CheckWeeklyCollect cwc);
    public boolean ModiMyCollectRemark(@Param("cwc") CheckWeeklyCollect cwc);
    public boolean delOneCollect(@Param("collectId") Integer collectId);
    public CheckWeeklyCollect selectExist(@Param("weeklyId") Integer weeklyId);
    public boolean delCollectByWeekly(@Param("weeklyId")Integer weeklyId);
}
