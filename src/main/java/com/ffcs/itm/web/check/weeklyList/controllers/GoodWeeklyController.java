package com.ffcs.itm.web.check.weeklyList.controllers;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.GoodWeekly;
import com.ffcs.itm.web.check.weeklyList.service.GoodWeeklyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/check/goodWeekly")
public class GoodWeeklyController {

    @Autowired
    private GoodWeeklyService goodWeeklyService;

    @RequestMapping(value="/goodWeeklyList.action",method = RequestMethod.GET)
    public PageInfo<GoodWeekly> addOneCollection(@RequestParam("pages") Integer pages,
                                                 @RequestParam("pageSize") Integer pageSize){
        return goodWeeklyService.getGoodWeeklyList(pages,pageSize);
    }

    @RequestMapping(value="/addGoodWeeklyList.action",method = RequestMethod.POST)
    public BaseResponse addOneCollection(@RequestBody HashMap<String,Object> map){
        return goodWeeklyService.addOneGoodWeekly(map);
    }

    @RequestMapping(value="/delGoodWeeklyList.action",method = RequestMethod.POST)
    public BaseResponse delOneCollection(@RequestBody HashMap<String,Object> map){
        return goodWeeklyService.delOneGoodWeekly(map);
    }



}
