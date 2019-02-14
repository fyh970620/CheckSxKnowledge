package com.ffcs.itm.web.check.checkSubject.controllers;

import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.check.checkKnow.service.CheckKnowListService;
import com.ffcs.itm.web.check.checkSubject.entity.CheckSubjectList;
import com.ffcs.itm.web.check.checkSubject.service.CheckSubjectListService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/check/checkSubject")
public class CheckSubjectListController {

    @Autowired
    private CheckSubjectListService checkSubjectListService;

    @RequestMapping(value="/getAllList.action",method = RequestMethod.GET)
    public List<CheckSubjectList> getAllList(){
        return checkSubjectListService.getAllList();
    }

    @RequestMapping(value="/getDetail.action",method = RequestMethod.GET)
    public CheckSubjectList getDetail(@RequestParam("subjectId")Integer subjectId){
        return checkSubjectListService.selectDetail(subjectId);
    }

    @RequestMapping(value="/getUpdate.action",method = RequestMethod.POST)
    public boolean  getDetail(@RequestBody HashMap<String,Object> map){
        return checkSubjectListService.updateOneSubject(map);
    }

    @RequestMapping(value="/addTogether.action",method = RequestMethod.POST)
    public boolean  getAddOneTogether(@RequestBody HashMap<String,Object> map){
        return checkSubjectListService.insertIntoTogether(map);
    }

    @RequestMapping(value="/addOnlyAttach.action",method = RequestMethod.POST)
    public boolean  getAddOnlyAttach(@RequestBody HashMap<String,Object> map){
        return checkSubjectListService.onlyInsert(map);
    }

    @RequestMapping(value="/delOnlyAttach.action",method = RequestMethod.POST)
    public boolean  getdelOnlyAttach(@RequestBody HashMap<String,Object> map){
        return checkSubjectListService.onlydelOneAttach(map);
    }

    @RequestMapping(value="/delOneCheckSubject.action",method = RequestMethod.POST)
    public boolean  getDelOneCheckSubject(@RequestBody HashMap<String,Object> map){
        return checkSubjectListService.delAll(map);
    }
}
