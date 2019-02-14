package com.ffcs.itm.web.check.weeklyList.controllers;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListComment;
import com.ffcs.itm.web.check.weeklyList.service.WeeklyListCommentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/check/weekLyComments")
public class WeeklyCommentsController {

    @Autowired
    private WeeklyListCommentsService weeklyListCommentsService;

    @RequestMapping(value="/getFyComments.action" ,method = RequestMethod.GET)
    public PageInfo<CheckWeeklyListComment> getFyComments(@RequestParam("pages")Integer pages,
                                                          @RequestParam("pageSize")Integer pageSize,
                                                          @RequestParam("weeklyId")Integer weeklyId){
        return weeklyListCommentsService.getAllCheckWeeklyById(pages,pageSize,weeklyId);
    }

    @RequestMapping(value="/addTopComments.action" ,method = RequestMethod.POST)
    public boolean addTopComments(@RequestBody HashMap<String,Object> map){
       return  weeklyListCommentsService.insertTopComments(map);
    }

    @RequestMapping(value="/addBackComments.action" ,method = RequestMethod.POST)
    public boolean addBackComments(@RequestBody HashMap<String,Object> map){
        return  weeklyListCommentsService.insertBackComments(map);
    }

    @RequestMapping(value="/delOneCommentFromList.action" ,method = RequestMethod.POST)
    public BaseResponse delOneCommentsFromList(@RequestBody HashMap<String,Object> map){
        return weeklyListCommentsService.delOneCommentFromList(map);
    }

    @RequestMapping(value="/getCountAvg.action" ,method = RequestMethod.GET)
    public BaseResponse selectCountAvg(@RequestParam("weeklyId") Integer weeklyId){
        return weeklyListCommentsService.selectCommentAvgCount(weeklyId);
    }

}
