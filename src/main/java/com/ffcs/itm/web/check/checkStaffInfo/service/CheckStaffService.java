package com.ffcs.itm.web.check.checkStaffInfo.service;

import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.checkStaffInfo.entity.CheckStaffInfo;
import com.ffcs.itm.web.check.checkStaffInfo.repository.CheckStaffInfoMapper;
import com.ffcs.itm.web.check.entity.StaffUser;
import com.ffcs.itm.web.check.utils.BaseResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckStaffService {

    @Autowired
    private CheckStaffInfoMapper checkStaffInfoMapper;
    @Autowired
    private TypeMapper typeMapper;

    public List<StaffUser> getListSx(){
        List<StaffUser> CsiLists = checkStaffInfoMapper.selectListSx();
        return CsiLists;
    }

    public BaseResponse addOneCheckStaffInfo(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csi"));
        BaseResponse bs = new BaseResponse();
        CheckStaffInfo csi = (CheckStaffInfo) JSONObject.toBean(jsonObject, CheckStaffInfo.class);
        Integer count = checkStaffInfoMapper.selectExist(csi.getStaffId());
        System.out.println("===>" +csi);
        if(count==0){
            boolean result =checkStaffInfoMapper.addOneStaffInfo(csi);
            if(result!=false){
                bs.setMsg("添加成功");
                bs.setCode(1);
                return bs;
            }else {
                bs.setMsg("添加失败");
                bs.setCode(0);
                return bs;
            }
        }else{
            bs.setMsg("已经存在");
            bs.setCode(0);
            return bs;
        }
    }

    public CheckStaffInfo selectMyselfMessage(Integer staffId){
        CheckStaffInfo csi = checkStaffInfoMapper.selectMySelfMessage(staffId);
        System.out.println("==>" + csi);
        csi.setBeginDate(csi.getBeginDate().substring(0,10));
        csi.setGroupTypevalue(typeMapper.selectGroupName(csi.getStaffGroupType()));
        return csi;
    }

    public boolean delMymessage(HashMap<String,Object> map){
        Integer staffId = (Integer)map.get("staffId");
        boolean result = checkStaffInfoMapper.delOneMessage(staffId);
        return result;
    }


    public boolean updateMymessage(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csi"));
        CheckStaffInfo csi = (CheckStaffInfo) JSONObject.toBean(jsonObject, CheckStaffInfo.class);
        System.out.println("===》" + csi);
        boolean result = checkStaffInfoMapper.updateOneMessage(csi);
        return result;
    }

    public PageInfo<CheckStaffInfo> getListAll(int pages, int pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckStaffInfo> lists = checkStaffInfoMapper.selectAllstaffInfoList();
        for(int i=0;i<lists.size();i++){
            lists.get(i).setGroupTypevalue(typeMapper.selectGroupName(lists.get(i).getStaffGroupType()));
            lists.get(i).setBeginDate(lists.get(i).getBeginDate().substring(0,10));
        }
        PageInfo<CheckStaffInfo> pageinfo = new PageInfo<CheckStaffInfo>(lists);
        return pageinfo;
    }

}
