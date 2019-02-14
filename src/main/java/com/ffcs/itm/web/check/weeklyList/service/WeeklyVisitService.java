package com.ffcs.itm.web.check.weeklyList.service;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyVisitList;
import com.ffcs.itm.web.check.weeklyList.repository.WeeklyVisitMapper;
import com.ffcs.itm.web.support.SessionInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class WeeklyVisitService {

    @Autowired
    private WeeklyVisitMapper weeklyVisitMapper;

    public PageInfo<CheckWeeklyVisitList> getVisitList(Integer weeklyId, Integer pages, Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyVisitList> lists = weeklyVisitMapper.selectVisitList(weeklyId);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        for(int i =0;i<lists.size();i++){
            lists.get(i).setTime(sdf.format(lists.get(i).getVisitCreateDate()));
        }
        PageInfo<CheckWeeklyVisitList> pageInfo = new PageInfo<CheckWeeklyVisitList>(lists);
        return pageInfo;
    }

    public Integer getCountVisits(Integer weeklyId){
        Integer count = weeklyVisitMapper.selectCount(weeklyId);
        return count;
    }

    public BaseResponse addWeeklyVisit(HashMap<String,Object> map){
        Integer staffId = (Integer) map.get("staffId");
        Integer weeklyId = (Integer) map.get("weeklyId");
        CheckWeeklyVisitList cwvl = weeklyVisitMapper.selectByTimeAdd(weeklyId);
        BaseResponse bs = new BaseResponse();
        if(cwvl !=null){
            Integer seconds = cwvl.getVisitCreateDate().getSeconds() + cwvl.getVisitCreateDate().getMinutes()*60;
            Integer houers = cwvl.getVisitCreateDate().getHours();
            Integer days = cwvl.getVisitCreateDate().getDate();
            Calendar c = Calendar.getInstance();//获取当前时间
            int minute = c.get(Calendar.MINUTE);
            int seconds2 = c.get(Calendar.SECOND)+minute*60;
            int hour = c.get(Calendar.HOUR);
            int day = c.get(Calendar.DAY_OF_MONTH);
            if(hour==houers && day == days){
                if((seconds2-seconds)/60 < 5){   //小于5分钟
                    boolean result = weeklyVisitMapper.updateOneVisit(cwvl.getVisitId());   //更新当前的时间
                    if(result!=false){
                        bs.setCode(1);
                        bs.setMsg("更新时间成功");
                        return bs;
                    }else{
                        bs.setCode(0);
                        bs.setMsg("更新时间失败");
                        return bs;
                    }
                }else{
                    boolean result = weeklyVisitMapper.addOneVisitTravel(staffId,weeklyId);   //添加新的访问记录
                    if(result!=false){
                        bs.setCode(1);
                        bs.setMsg("添加新的访问记录");
                        return bs;
                    }else {
                        bs.setCode(0);
                        bs.setMsg("添加新的访问记录失败");
                        return bs;
                    }
                }
            }else{
                boolean result = weeklyVisitMapper.addOneVisitTravel(staffId,weeklyId);  //添加新的访问记录
                if(result!=false){
                    bs.setCode(1);
                    bs.setMsg("添加新的访问记录");
                    return bs;
                }else {
                    bs.setCode(0);
                    bs.setMsg("添加新的访问记录失败");
                    return bs;
                }
            }
        }else {
            boolean result = weeklyVisitMapper.addOneVisitTravel(staffId,weeklyId);   //添加新的访问记录
            if(result!=false){
                bs.setCode(1);
                bs.setMsg("添加新的访问记录");
                return bs;
            }else {
                bs.setCode(0);
                bs.setMsg("添加新的访问记录失败");
                return bs;
            }
        }
    }

    public boolean delVisitByWeekly(Integer weeklyId){
        boolean result = weeklyVisitMapper.delVisitByWeekly(weeklyId);
        return result;
    }


}
