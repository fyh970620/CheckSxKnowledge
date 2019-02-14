package com.ffcs.itm.web.check.weeklyList.repository;

import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyVisitList;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WeeklyVisitMapper {

    public List<CheckWeeklyVisitList> selectVisitList(@Param("weeklyId") Integer weeklyId);
    public boolean addOneVisitTravel(@Param("staffId")Integer staffId,@Param("weeklyId")Integer weeklyId);
    public CheckWeeklyVisitList selectByTimeAdd(@Param("weeklyId")Integer weeklyId);
    public boolean updateOneVisit(@Param("visitId")Integer visitId);
    public Integer selectCount(@Param("weeklyId")Integer weeklyId);
    public boolean delVisitByWeekly(@Param("weeklyId")Integer weeklyId);
}
