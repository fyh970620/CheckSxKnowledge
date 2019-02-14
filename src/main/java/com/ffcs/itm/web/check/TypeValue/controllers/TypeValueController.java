package com.ffcs.itm.web.check.TypeValue.controllers;


import com.ffcs.itm.web.check.TypeValue.service.TypeValueService;
import com.ffcs.itm.web.check.entity.TpDomainListvalues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value="/check/type")
public class TypeValueController {

    @Autowired
    private TypeValueService typeValueService;

    @RequestMapping(value="/getListGroupType.action" ,method = RequestMethod.GET)
    public List<TpDomainListvalues> getListGroupType(){
       return  typeValueService.selectAllGroupType();
    }

    @RequestMapping(value="/getListStaffType.action" ,method = RequestMethod.GET)
    public List<TpDomainListvalues> getListStaffType(){
        return  typeValueService.selectAllStaffType();
    }

    @RequestMapping(value="/getListType.action" ,method = RequestMethod.GET)
    public List<TpDomainListvalues> getListType(){
        return  typeValueService.selectAllListType();
    }

    @RequestMapping(value="/getSubjectClass.action",method = RequestMethod.GET)
    public List<Map> getDateSelecting(){
        List<Map> lists  = new ArrayList<Map>();
        Calendar date = Calendar.getInstance();
        Integer year = Integer.valueOf(date.get(Calendar.YEAR));
        for(int i=2015; i<=year;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("key",""+i);
            map.put("value",""+i+"届");
            lists.add(map);
        }
        return lists;


    }
}
