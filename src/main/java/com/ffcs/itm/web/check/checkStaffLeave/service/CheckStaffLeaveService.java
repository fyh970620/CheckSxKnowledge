package com.ffcs.itm.web.check.checkStaffLeave.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.checkStaffLeave.entity.CheckStaffLeave;
import com.ffcs.itm.web.check.checkStaffLeave.repository.CheckStaffLeaveMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CheckStaffLeaveService {

    @Autowired
    private CheckStaffLeaveMapper checkStaffLeaveMapper;
    @Autowired
    private TypeMapper typeMapper;

    public PageInfo<CheckStaffLeave> getYourListLeave(Integer staffId,Integer pages,Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckStaffLeave> leaveList =  checkStaffLeaveMapper.selectYourCheckMap(staffId);
        System.out.println("========>" +leaveList);
        for(int i=0;i<leaveList.size();i++){
            leaveList.get(i).setBeginLeaveDate(leaveList.get(i).getBeginLeaveDate().substring(0,10));
            leaveList.get(i).setEndLeaveDate(leaveList.get(i).getEndLeaveDate().substring(0,10));
            leaveList.get(i).setGroupTypeValue(typeMapper.selectGroupName(leaveList.get(i).getStaffGroupType()));
        }
        PageInfo<CheckStaffLeave> pageInfo = new PageInfo<CheckStaffLeave>(leaveList);
        return pageInfo;
    }

    public boolean addOneListLeave(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csl"));
        CheckStaffLeave csl = (CheckStaffLeave) JSONObject.toBean(jsonObject,CheckStaffLeave.class);
        boolean result = checkStaffLeaveMapper.addOneStaffLeave(csl);
        if(result!=false){
           return result;
        }else{
           return false;
        }
    }

    public boolean delOneCsl(HashMap<String,Object> map){
        Integer leaveId = (Integer)map.get("leaveId");
        boolean result = checkStaffLeaveMapper.delOneStaffLeave(leaveId);
        if(result!=false){
            return result;
        }else{
            return false;
        }
    }
}
