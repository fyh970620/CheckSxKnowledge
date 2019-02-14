package com.ffcs.itm.web.check.weeklyList.repository;

import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListAttach;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface WeeklyListMapper {

    /**
     * 查所有count+pagesize
     */
    public List<CheckWeeklyList> selectWeekListFy();
    public List<CheckWeeklyListAttach> selectWeekListAttach(@Param("weeklyId") Integer weeklyId);
    public StaffUser selectStaff(@Param("staffId") Integer staffId);

    /**
     * 模糊查询
     */
    public List<CheckWeeklyList> selectLikely(@Param("keywords") String keywords);

    /**
     * 查看详情
     */
    public CheckWeeklyList selectOnedetail(@Param("weeklyId") Integer weeklyId);
    public List<CheckWeeklyListAttach> selectdetailAttach(@Param("weeklyId") Integer weeklyId);

    /**
     * 修改
     */
    public boolean updateOne(@Param("cwl") CheckWeeklyList cwl);
    /**
     *删除
     */
    public boolean delOneFirst(@Param("weeklyId") Integer weeklyId);
    public boolean delOneSecond(@Param("weeklyId") Integer weeklyId);

    /**
     * 添加周报
     */
    public boolean addOneWeekly(@Param("cwl") CheckWeeklyList cwl);
    public Integer addWeeklyAttach(@Param("cwla") CheckWeeklyListAttach cwla);

    /**
     * 已有周报添加
     */
    public boolean OnlyAddWeeklyAttach(@Param("cwla") CheckWeeklyListAttach cwla);

    /**
     * 查自己的周报
     */
    public List<CheckWeeklyList> selectMyselfWeekly(@Param("staffId")Integer staffId);
}
