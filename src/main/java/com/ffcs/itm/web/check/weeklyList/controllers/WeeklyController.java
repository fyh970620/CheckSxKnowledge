package com.ffcs.itm.web.check.weeklyList.controllers;

import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.weeklyList.service.WeekLyListService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/check/weekLy")
public class WeeklyController {

    @Autowired
    private WeekLyListService weekLyListService;

    @RequestMapping(value="/getListWeely.action",method = RequestMethod.GET)
    public PageInfo<CheckWeeklyList> getListWeekly(@RequestParam("pages") Integer pages,
                                                   @RequestParam("pageSize") Integer pageSize){
         return  weekLyListService.selectWeekListFy(pages,pageSize);
    }

    @RequestMapping(value="/getMyselfListWeely.action",method = RequestMethod.GET)
    public PageInfo<CheckWeeklyList> getListWeekly(@RequestParam("pages") Integer pages,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("staffId") Integer staffId){
        return  weekLyListService.selectMyselfWeekly(pages,pageSize,staffId);
    }

    @RequestMapping(value="/getListByLikely.action" ,method = RequestMethod.GET)
    public List<CheckWeeklyList> getListFromLikely(@RequestParam("keywords") String keywords){
        List<CheckWeeklyList> likelists = weekLyListService.selectLikely(keywords);
        return likelists;
    }

    @RequestMapping(value="/getWeeklyDetail.action" ,method = RequestMethod.GET)
    public CheckWeeklyList getOneDetail(@RequestParam("weeklyId") Integer weeklyId){
        CheckWeeklyList cwl = weekLyListService.selectOneDetail(weeklyId);
       return cwl;
    }

    @RequestMapping(value="/updateWeeklyList.action" ,method = RequestMethod.POST)
    public boolean updateOneWeekly(@RequestBody HashMap<String,Object> map){
        boolean result = weekLyListService.UpdateOne(map);
        return result;
    }

    @RequestMapping(value="/delWeeklyList.action" ,method = RequestMethod.POST)
    public boolean delOneWeekly(@RequestBody JSONObject object){
       boolean result =  weekLyListService.delOneCheckWeekly(object);
       return result;
    }

    @RequestMapping(value="/addOneWeekly.action" ,method = RequestMethod.POST)
    public boolean addOneWeekly(@RequestBody HashMap<String,Object> map){
       boolean result =  weekLyListService.insertOneWeekly(map);
       return result;
    }

    @RequestMapping(value="/addOnlyWeekLyAttach",method = RequestMethod.POST)
    public boolean addOnlyWeekAttach(@RequestBody HashMap<String,Object> map){
        boolean result = weekLyListService.OnlyAddAttach(map);
        return result;
    }

}
