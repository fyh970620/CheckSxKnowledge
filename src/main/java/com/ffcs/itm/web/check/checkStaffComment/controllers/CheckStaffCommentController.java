package com.ffcs.itm.web.check.checkStaffComment.controllers;

import com.ffcs.itm.web.check.checkStaffComment.entity.CheckStaffComment;
import com.ffcs.itm.web.check.checkStaffComment.service.CheckStaffCommentService;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.utils.BaseResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/check/csComments")
public class CheckStaffCommentController {

    @Autowired
    private CheckStaffCommentService checkStaffCommentService;

    @RequestMapping(value="/getListSxByGType.action",method = RequestMethod.GET)
    public PageInfo<CheckStaffComment> getListCheckStaffCommentsFy(@RequestParam("pages")Integer pages,
                                                                   @RequestParam("pageSize")Integer pageSize){
        PageInfo<CheckStaffComment> pageInfo = checkStaffCommentService.getListStaffComments(pages,pageSize);
        return pageInfo;
    }

    @RequestMapping(value="/addCheckStaffComment.action",method = RequestMethod.POST)
    public BaseResponse AddCheckStaffComment(@RequestBody HashMap<String,Object>map){
        return  checkStaffCommentService.addOneStaffComment(map);
    }

    @RequestMapping(value="/updateOneStaffComment.action",method = RequestMethod.POST)
    public boolean updateOneCheckStaffComment(@RequestBody HashMap<String,Object>map){
        return checkStaffCommentService.updateOneStaffComment(map);
    }

    /**
     * 查看该人下的所有考核内容
     * @param checkStaffId
     * @param pages
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/getDetail.action",method = RequestMethod.GET)
    public PageInfo<CheckStaffComment> getDetail(@RequestParam("checkStaffId")Integer checkStaffId,
                                       @RequestParam("pages")Integer pages,
                                       @RequestParam("pageSize")Integer pageSize){
       return  checkStaffCommentService.findDetail(checkStaffId,pages,pageSize);
    }


}
