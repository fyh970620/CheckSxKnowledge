package com.ffcs.itm.web.check.weeklyList.repository;

import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.weeklyList.entity.GoodWeekly;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface GoodWeeklyMapper {
    /**
     * 优秀分页推荐列表
     */
    public List<GoodWeekly> selectAllGoodWeekly();
    public StaffUser GoodWeeklyByStaff(@Param("topStaffId") Integer topStaffId);
    public CheckWeeklyList selectTopWeeklyName(@Param("weeklyId") Integer weeklyId);

    /**
     * 添加优秀推荐<验证不可以重复推荐></>
     */
    public GoodWeekly checkTopAgain(@Param("staffId") Integer staffId, @Param("weeklyId") Integer weeklyId);
    public boolean addOneGoodWeekly(@Param("staffId") Integer staffId, @Param("weeklyId") Integer weeklyId);

    /**
     * 删除你的推荐
     */
    public GoodWeekly SelectGoodCheckStaffId(@Param("topId") Integer topId);
    public boolean delCheckGoodWeekly(@Param("topId") Integer topId, @Param("staffId") Integer staffId);

    /**
     * 删除周报-》删除周报下的所有的推荐
     */
    public boolean delCheckGoodByWeekly(@Param("weeklyId")Integer weeklyId);
}
