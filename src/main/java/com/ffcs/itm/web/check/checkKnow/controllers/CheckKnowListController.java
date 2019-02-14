package com.ffcs.itm.web.check.checkKnow.controllers;

import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.check.checkKnow.service.CheckKnowListService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/Check/CheckKnow")
public class CheckKnowListController {

    @Autowired
    private CheckKnowListService checkKnowListService;

    @RequestMapping(value = "/getCheckKnowList.action", method = RequestMethod.GET)
    public PageInfo<CheckKonwList> getListByPageHelper(@RequestParam("pages") Integer pages,
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestParam("knowGroupType") String knowGroupType){
        return checkKnowListService.getListCheckKnow(pages,pageSize,knowGroupType);

    }

    @RequestMapping(value = "/addOneCheckKnow.action", method = RequestMethod.POST)
    public boolean addOneKnowList(@RequestBody HashMap<String,Object> map){
        boolean result = checkKnowListService.AddOneAttach(map);
        return result;
    }

    @RequestMapping(value="/delOneAttach.action",method = RequestMethod.POST)
    public boolean delOneAttach(@RequestBody JSONObject object){
       return checkKnowListService.deleteOneCheckKnow(object);
    }

    @RequestMapping(value="/delOnlyAttach.action",method = RequestMethod.POST)
    public boolean delOnlyAttach(@RequestBody HashMap<String,Object>map){
        return checkKnowListService.delOnlyAttach(map);
    }


    @RequestMapping(value="/updateOneAttachAndKnow.action",method = RequestMethod.POST)
    public boolean updateAttachAndKnow(@RequestBody HashMap<String,Object> map){
        return checkKnowListService.UpdateCKLandCKLA(map);
    }

    @RequestMapping(value="/addExtraAttach.action" ,method = RequestMethod.POST)
    public boolean addExtraAttach(@RequestBody HashMap<String,Object> map){
        return checkKnowListService.addExtraAttach(map);
    }

}
