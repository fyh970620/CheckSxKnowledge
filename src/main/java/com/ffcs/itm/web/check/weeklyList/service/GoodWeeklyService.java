package com.ffcs.itm.web.check.weeklyList.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.GoodWeekly;
import com.ffcs.itm.web.check.weeklyList.repository.GoodWeeklyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodWeeklyService {

    @Autowired
    private GoodWeeklyMapper goodWeeklyMapper;
    @Autowired
    private TypeMapper typeMapper;

    public PageInfo<GoodWeekly> getGoodWeeklyList(Integer pages, Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<GoodWeekly> lists =  goodWeeklyMapper.selectAllGoodWeekly();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        for(int i=0;i<lists.size();i++){
            lists.get(i).setTime(sdf.format(lists.get(i).getVisitCreateDate()));
           lists.get(i).setStaff( goodWeeklyMapper.GoodWeeklyByStaff(lists.get(i).getTopStaffId()));
           lists.get(i).setCwl( goodWeeklyMapper.selectTopWeeklyName(lists.get(i).getWeeklyId()));
           lists.get(i).getCwl().setSubjectGroupTypeValue(typeMapper.selectGroupName(lists.get(i).getCwl().getSubjectGroupType()));
        }
        PageInfo<GoodWeekly> gws = new PageInfo<GoodWeekly>(lists);
        return gws;
    }

    public BaseResponse addOneGoodWeekly(HashMap<String,Object> map){
        BaseResponse bs = new BaseResponse();
        Integer weeklyId = (Integer) map.get("weeklyId");
        Integer staffId = (Integer) map.get("staffId");
        GoodWeekly gw = goodWeeklyMapper.checkTopAgain(staffId,weeklyId);
        if(gw==null){
            boolean result = goodWeeklyMapper.addOneGoodWeekly(staffId,weeklyId);
            if(result!=false){
                bs.setCode(1);
                bs.setMsg("推荐成功");
                return bs;
            }else{
                bs.setCode(0);
                bs.setMsg("推荐失败");
                return bs;
            }
        }else{
            bs.setCode(0);
            bs.setMsg("已经被您推荐，请不要重复推荐");
            return bs;
        }
    }

    public  BaseResponse delOneGoodWeekly(HashMap<String,Object> map){
        BaseResponse bs = new BaseResponse();
        Integer staffId = (Integer) map.get("staffId");
        Integer topId = (Integer) map.get("topId");
        GoodWeekly gw = goodWeeklyMapper.SelectGoodCheckStaffId(topId);
        if(gw.getTopStaffId() == staffId){
            boolean result =  goodWeeklyMapper.delCheckGoodWeekly(topId,staffId);
            if(result!=false){
                bs.setCode(1);
                bs.setMsg("删除成功");
                return bs;
            }else{
                bs.setCode(0);
                bs.setMsg("删除失败");
                return bs;
            }
        }else{
            bs.setMsg("只能删除自己的推荐项");
            bs.setCode(0);
            return bs;
        }
    }

    /**
     * 删除周报删除该推荐
     */
    public boolean delGoodWeeklyByWeekly(Integer weeklyId){
       boolean result =  goodWeeklyMapper.delCheckGoodByWeekly(weeklyId);
       return result;
    }
}
