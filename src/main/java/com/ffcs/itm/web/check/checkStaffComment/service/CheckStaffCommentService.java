package com.ffcs.itm.web.check.checkStaffComment.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.checkStaffComment.entity.CheckStaffComment;
import com.ffcs.itm.web.check.checkStaffComment.repository.CheckStaffCommentMapper;
import com.ffcs.itm.web.check.utils.BaseResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CheckStaffCommentService {

    @Autowired
    private CheckStaffCommentMapper checkStaffCommentMapper;
    @Autowired
    private TypeMapper typeMapper;

    public PageInfo<CheckStaffComment> getListStaffComments(Integer pages,Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckStaffComment> list = checkStaffCommentMapper.selectCheckStaffComment();
        for(int i=0;i<list.size();i++){
            list.get(i).setStaffChecked(checkStaffCommentMapper.selectStaffDetail(list.get(i).getCheckStaffId())); //被考核人信息
            list.get(i).setStaffCheck(checkStaffCommentMapper.selectStaffDetail(list.get(i).getStaffId())); //考核人信息
            list.get(i).setGroupTypeValue(typeMapper.selectGroupName(list.get(i).getStaffGroupType()));
        }
        PageInfo<CheckStaffComment> listComments = new PageInfo<CheckStaffComment>(list);
        return listComments;
    }

    public BaseResponse addOneStaffComment(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csc"));
        CheckStaffComment csc = (CheckStaffComment)JSONObject.toBean(jsonObject,CheckStaffComment.class);
        System.out.println("===>" + csc);
        boolean result = checkStaffCommentMapper.addOneCheckStaffComment(csc);
        BaseResponse bs = new BaseResponse();
        if(result!=false){
            bs.setCode(1);
            bs.setMsg("添加成功");
            return bs;
        }else{
            bs.setCode(0);
            bs.setMsg("添加失败");
            return bs;
        }
    }

    public boolean updateOneStaffComment(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csc"));
        CheckStaffComment csc = (CheckStaffComment)JSONObject.toBean(jsonObject,CheckStaffComment.class);
        boolean result = checkStaffCommentMapper.updateOneCheckStaffComment(csc);
        if(result!=false){
            return result;
        }else{
            return false;
        }
    }

    public PageInfo<CheckStaffComment> findDetail(Integer checkStaffId,Integer pages,Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckStaffComment> csc = checkStaffCommentMapper.selectDetail(checkStaffId);
        for(int i=0;i<csc.size();i++){
            csc.get(i).setStaffChecked(checkStaffCommentMapper.selectStaffDetail(csc.get(i).getCheckStaffId())); //被考核人信息
            csc.get(i).setStaffCheck(checkStaffCommentMapper.selectStaffDetail(csc.get(i).getStaffId())); //考核人信息
            csc.get(i).setGroupTypeValue(typeMapper.selectGroupName(csc.get(i).getStaffGroupType()));
            csc.get(i).setVisitCreateDate(csc.get(i).getVisitCreateDate().substring(0,10));
        }
        PageInfo<CheckStaffComment> pageinfo = new PageInfo<CheckStaffComment>(csc);
        return pageinfo;
    }



}
