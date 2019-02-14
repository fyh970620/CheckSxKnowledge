package com.ffcs.itm.web.check.weeklyList.service;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyCollect;
import com.ffcs.itm.web.check.weeklyList.repository.WeeklyCollectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class WeeklyCollectService {

    @Autowired
    private WeeklyCollectMapper weeklyCollectMapper;

    public PageInfo<CheckWeeklyCollect> getMyWeeklyCollections(Integer staffId, Integer pages, Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyCollect> lists = weeklyCollectMapper.getMyCollects(staffId);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        PageInfo<CheckWeeklyCollect> cwcs = new PageInfo<CheckWeeklyCollect>(lists);
        for(int i=0;i<cwcs.getList().size();i++){
            cwcs.getList().get(i).setTime(sdf.format(cwcs.getList().get(i).getVisitCreateDate()));
            cwcs.getList().get(i).setStaff(weeklyCollectMapper.selectStaffname(staffId));
            cwcs.getList().get(i).setCwl(weeklyCollectMapper.selectWeekListName(cwcs.getList().get(i).getWeeklyId()));
        }
        return cwcs;
    }

    public BaseResponse addOneWeeklyCollections(HashMap<String,Object> map){
        JSONObject jsonObject=JSONObject.fromObject(map.get("cwc"));
        CheckWeeklyCollect cwc=(CheckWeeklyCollect) JSONObject.toBean(jsonObject, CheckWeeklyCollect.class);
        CheckWeeklyCollect cwc2 = weeklyCollectMapper.selectExist(cwc.getWeeklyId());
        if(cwc2==null){
            boolean result = weeklyCollectMapper.AddOneCollect(cwc);
            if(result!=false){
                BaseResponse br = new BaseResponse();
                br.setCode(1);
                br.setMsg("收藏成功");
                return br;
            }else{
                BaseResponse br = new BaseResponse();
                br.setCode(0);
                br.setMsg("收藏失败");
                return br;
            }
        }else{
            System.err.println("已经被收藏");
            BaseResponse br = new BaseResponse();
            br.setCode(0);
            br.setMsg("已经被收藏");
            return br;
        }
    }

    public boolean ModifyerCollectionRemark(HashMap<String,Object> map) {
        JSONObject jsonObject = JSONObject.fromObject(map.get("cwc"));
        CheckWeeklyCollect cwc = (CheckWeeklyCollect) JSONObject.toBean(jsonObject, CheckWeeklyCollect.class);
        boolean result = weeklyCollectMapper.ModiMyCollectRemark(cwc);
        if(result!=false){
            return true;
        }else{
            return false;
        }
    }

    public boolean delOneCollection(HashMap<String,Object> map){
        Integer collectId = (Integer) map.get("collectId");
        boolean result = weeklyCollectMapper.delOneCollect(collectId);
        if(result!=false){
            return true;
        }else{
            return false;
        }
    }

    public boolean delCollectFromWeekly(Integer weeklyId){
        boolean result = weeklyCollectMapper.delCollectByWeekly(weeklyId);
        return result;
    }
}
