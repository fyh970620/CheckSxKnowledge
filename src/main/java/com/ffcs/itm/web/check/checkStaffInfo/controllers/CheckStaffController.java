package com.ffcs.itm.web.check.checkStaffInfo.controllers;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.checkStaffInfo.entity.CheckStaffInfo;
import com.ffcs.itm.web.check.checkStaffInfo.service.CheckStaffService;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.utils.BaseResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/check/CheckStaff")
public class CheckStaffController {

    @Autowired
    private CheckStaffService checkStaffService;

    @RequestMapping(value="/getListSxByGType.action",method = RequestMethod.GET)
    public List<StaffUser> listSx(){
        List<StaffUser> csilists = checkStaffService.getListSx();
        return csilists;
    }

    @RequestMapping(value="/getMyselfMessage.action",method = RequestMethod.GET)
    public CheckStaffInfo selectMySelfMessage(@RequestParam("staffId")Integer staffId){
        return checkStaffService.selectMyselfMessage(staffId);
    }


    @RequestMapping(value="/addOneStaffInfo.action",method = RequestMethod.POST)
    public BaseResponse addOneStaffInfo(@RequestBody HashMap<String, Object> map){
        return checkStaffService.addOneCheckStaffInfo(map);
    }

    @RequestMapping(value="/delOneStaffInfo.action",method = RequestMethod.POST)
    public boolean delOneStaffInfo(@RequestBody HashMap<String, Object> map){
        return checkStaffService.delMymessage(map);
    }

    @RequestMapping(value="/updateOneStaffInfo.action",method = RequestMethod.POST)
    public boolean updateOneStaffInfo(@RequestBody HashMap<String, Object> map){
        return checkStaffService.updateMymessage(map);
    }


    @RequestMapping(value="/selectListAll.action",method = RequestMethod.GET)
    public PageInfo<CheckStaffInfo> selectListAll(int pages, int pageSize){
        return checkStaffService.getListAll(pages,pageSize);
    }

}
