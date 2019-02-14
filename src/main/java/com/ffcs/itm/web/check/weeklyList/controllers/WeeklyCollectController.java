package com.ffcs.itm.web.check.weeklyList.controllers;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyCollect;
import com.ffcs.itm.web.check.weeklyList.service.WeeklyCollectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/check/weekLyCollect")
public class WeeklyCollectController {

    @Autowired
    private WeeklyCollectService weeklyCollectService;

    @RequestMapping(value="/getMyWeeklyCollects.action",method = RequestMethod.GET)
    public PageInfo<CheckWeeklyCollect> getMyCollects(@RequestParam("staffId")Integer staffId,
                                                      @RequestParam("pages") Integer pages,
                                                      @RequestParam("pageSize") Integer pageSize){
        PageInfo<CheckWeeklyCollect> cwcs = weeklyCollectService.getMyWeeklyCollections(staffId,pages,pageSize);
        return cwcs;
    }

    @RequestMapping(value="/addOneCollect.action",method = RequestMethod.POST)
    public BaseResponse addOneCollection(@RequestBody HashMap<String,Object> map){
        BaseResponse baseResponse  =  weeklyCollectService.addOneWeeklyCollections(map);
        return baseResponse;
    }

    @RequestMapping(value="/updateOneCollect.action",method = RequestMethod.POST)
    public boolean updateOneCollection(@RequestBody HashMap<String,Object> map){
        boolean result =  weeklyCollectService.ModifyerCollectionRemark(map);
        return result;
    }

    @RequestMapping(value="/delOneCollect.action",method = RequestMethod.POST)
    public boolean deleteOneCollection(@RequestBody HashMap<String,Object> map){
        boolean result = weeklyCollectService.delOneCollection(map);
        return result;
    }



}
