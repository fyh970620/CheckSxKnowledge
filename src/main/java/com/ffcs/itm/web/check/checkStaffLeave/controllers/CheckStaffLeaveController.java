package com.ffcs.itm.web.check.checkStaffLeave.controllers;


import com.ffcs.itm.web.check.checkStaffLeave.entity.CheckStaffLeave;
import com.ffcs.itm.web.check.checkStaffLeave.service.CheckStaffLeaveService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/check/staffleave")
public class CheckStaffLeaveController {

    @Autowired
    private CheckStaffLeaveService checkStaffLeaveService;

    @RequestMapping(value="/getStaffLeaveList.action",method = RequestMethod.GET)
    public PageInfo<CheckStaffLeave> getLeaveList(@RequestParam("pages")Integer pages,
                                                  @RequestParam("staffId")Integer staffId,
                                                  @RequestParam("pageSize")Integer pageSize){
        return checkStaffLeaveService.getYourListLeave(staffId,pages,pageSize);
    }

    @RequestMapping(value="/addOneCsl.action",method = RequestMethod.POST)
    public boolean AddOneCsl(@RequestBody HashMap<String,Object> map){
        return checkStaffLeaveService.addOneListLeave(map);
    }

    @RequestMapping(value="/delOneCsl.action",method = RequestMethod.POST)
    public boolean delOneCsl(@RequestBody HashMap<String,Object> map){
        return checkStaffLeaveService.delOneCsl(map);
    }




}
