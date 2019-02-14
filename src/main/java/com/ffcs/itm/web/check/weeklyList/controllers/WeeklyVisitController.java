package com.ffcs.itm.web.check.weeklyList.controllers;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyVisitList;
import com.ffcs.itm.web.check.weeklyList.service.WeekLyListService;
import com.ffcs.itm.web.check.weeklyList.service.WeeklyVisitService;
import com.github.pagehelper.PageInfo;
import org.bouncycastle.jce.provider.symmetric.ARC4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/check/visit")
public class WeeklyVisitController {

    @Autowired
    private WeeklyVisitService weeklyVisitService;

    @RequestMapping(value="/checkVisit.action",method = RequestMethod.GET)
    public PageInfo<CheckWeeklyVisitList> getList(@RequestParam("weeklyId")Integer weeklyId,
                                                  @RequestParam("pages")Integer pages,
                                                  @RequestParam("pageSize")Integer pageSize){
        return weeklyVisitService.getVisitList(weeklyId,pages,pageSize);
    }

    @RequestMapping(value="/addVisitOne.action",method = RequestMethod.POST)
    public BaseResponse AddOneList(@RequestBody HashMap<String,Object>map){
        return weeklyVisitService.addWeeklyVisit(map);
    }

    @RequestMapping(value="/getCountVisit.action",method = RequestMethod.GET)
    public Integer getVisitCount(@RequestParam("weeklyId")Integer weeklyId){
        return weeklyVisitService.getCountVisits(weeklyId);
    }


}
