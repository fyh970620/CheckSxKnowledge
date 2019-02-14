package com.ffcs.itm.web.check.weeklyList.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.entity.CheckWeeklyList;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListAttach;
import com.ffcs.itm.web.check.weeklyList.repository.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class WeekLyListService {

    @Autowired
    private WeeklyListMapper weeklyListMapper;
    @Autowired
    private GoodWeeklyMapper goodWeeklyMapper;
    @Autowired
    private WeeklyCollectMapper weeklyCollectMapper;
    @Autowired
    private WeeklyListCommentsMapper weeklyListCommentsMapper;
    @Autowired
    private WeeklyVisitMapper weeklyVisitMapper;
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 显示与分页
     * @param pageSize
     * @param pages
     * @return
     */
    public PageInfo<CheckWeeklyList> selectWeekListFy(Integer pages, Integer pageSize){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyList> listChecks = weeklyListMapper.selectWeekListFy();
        PageInfo<CheckWeeklyList> cwls = new PageInfo<CheckWeeklyList>(listChecks);
        for (int i = 0; i < cwls.getList().size() ; i++) {
            CheckWeeklyList cwl =  cwls.getList().get(i);
            cwl.setStaffTypeValue(typeMapper.selectStaffTypeName(cwl.getStaffType()));
            cwl.setSubjectGroupTypeValue(typeMapper.selectGroupName(cwl.getSubjectGroupType()));
            cwl.setWeeklyTypeValue(typeMapper.selectListTypeName(cwl.getWeeklyType()));
            if(weeklyListMapper.selectWeekListAttach(cwl.getWeeklyId())!=null){
                cwl.setStaff(weeklyListMapper.selectStaff(cwl.getCreateStaffId()));
                cwl.setCwlas(weeklyListMapper.selectWeekListAttach(cwl.getWeeklyId()));
            }else{
                cwl.setStaff(weeklyListMapper.selectStaff(cwl.getCreateStaffId()));
                continue;
            }

        }
        return cwls;

    }

    /**
     * 我的 周报
     */
    public PageInfo<CheckWeeklyList> selectMyselfWeekly(Integer pages, Integer pageSize,Integer staffId){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyList> listChecks = weeklyListMapper.selectMyselfWeekly(staffId);
        PageInfo<CheckWeeklyList> cwls = new PageInfo<CheckWeeklyList>(listChecks);
        for (int i = 0; i < cwls.getList().size() ; i++) {
            CheckWeeklyList cwl =  cwls.getList().get(i);
            cwl.setSubjectGroupTypeValue(typeMapper.selectGroupName(cwl.getSubjectGroupType()));
            cwl.setWeeklyType(typeMapper.selectListTypeName(cwl.getWeeklyTypeValue()));
            if(weeklyListMapper.selectWeekListAttach(cwl.getWeeklyId())!=null){
                cwl.setCwlas(weeklyListMapper.selectWeekListAttach(cwl.getWeeklyId()));
            }else{
                cwl.setStaff(weeklyListMapper.selectStaff(cwl.getCreateStaffId()));
                continue;
            }

        }
        return cwls;
    }

    /**
     * 模糊条件查询
     * @param keywords
     * @return
     */
    public List<CheckWeeklyList> selectLikely(String keywords){
        List<CheckWeeklyList> cwls = weeklyListMapper.selectLikely(keywords);
        for(int i =0;i<cwls.size();i++){
            cwls.get(i).setStaffTypeValue(typeMapper.selectStaffTypeName(cwls.get(i).getStaffType()));
            cwls.get(i).setSubjectGroupTypeValue(typeMapper.selectGroupName( cwls.get(i).getSubjectGroupType()));
            cwls.get(i).setWeeklyType(typeMapper.selectListTypeName( cwls.get(i).getWeeklyTypeValue()));
        }
        return cwls;
    }

    /**
     * 查看周报详情
     */
    public CheckWeeklyList selectOneDetail(Integer weekly_id){
        CheckWeeklyList cwl = weeklyListMapper.selectOnedetail(weekly_id);
        System.out.println("============>" +cwl);
        cwl.setStaffTypeValue(typeMapper.selectStaffTypeName(cwl.getStaffType()));
        cwl.setSubjectGroupTypeValue(typeMapper.selectGroupName(cwl.getSubjectGroupType()));
        cwl.setWeeklyTypeValue(typeMapper.selectListTypeName(cwl.getWeeklyType()));
        if(cwl!=null){
           List<CheckWeeklyListAttach> lists =  weeklyListMapper.selectdetailAttach(weekly_id);
           if(lists!=null){
               cwl.setCwlas(lists);
                return cwl;
           }else{
               return cwl;
           }

        }else{
            return null;
        }

    }

    /**
     * 修改
     * 只关于修改周报列表
     */
    public boolean UpdateOne(HashMap<String,Object> map){
        Integer staffId = (Integer)map.get("staffId");
        JSONObject jsonObject=JSONObject.fromObject(map.get("cwl"));
        CheckWeeklyList cwl=(CheckWeeklyList)JSONObject.toBean(jsonObject, CheckWeeklyList.class);
        boolean result = weeklyListMapper.updateOne(cwl);
        if(result!=false){
            return result;
        }else{
            return true;
        }

    }

    /**
     * 删除
     */
    public boolean delOneCheckWeekly(JSONObject object){
        Integer weeklyId = object.getInt("weeklyId");
        boolean result = weeklyListMapper.delOneFirst(weeklyId);  //删除附件
        boolean result2 = goodWeeklyMapper.delCheckGoodByWeekly(weeklyId); //删除推荐
        boolean result3= weeklyCollectMapper.delCollectByWeekly(weeklyId); //删除收藏
        boolean result4 = weeklyListCommentsMapper.delCommentsByWeekly(weeklyId);  //删除评论
        boolean result5 = weeklyVisitMapper.delVisitByWeekly(weeklyId); //删除浏览量
        boolean endResult = weeklyListMapper.delOneSecond(weeklyId);// 删除周报
        if(endResult!=false){
            System.out.println("删除周报成功");
            return endResult;
        }else{
            System.out.println("删除周报失败");
            return false;
        }
    }

    /**
     * 添加信息
     */
    public boolean insertOneWeekly(HashMap<String,Object> map){
        JSONObject jsonObject=JSONObject.fromObject(map.get("cwl"));
        CheckWeeklyList cwl=(CheckWeeklyList)JSONObject.toBean(jsonObject, CheckWeeklyList.class);
        boolean result = weeklyListMapper.addOneWeekly(cwl);
        JSONArray jsonArray=JSONArray.fromObject(map.get("cwlas"));
        List<CheckWeeklyListAttach> cwlas=(List<CheckWeeklyListAttach>)JSONArray.toList(jsonArray, CheckWeeklyListAttach.class);
        if(cwlas!=null){
            for (int i = 0; i < cwlas.size() ; i++) {
                Integer result2 = weeklyListMapper.addWeeklyAttach(cwlas.get(i));
                if(result2>0 & result!=false){
                    if(i==cwlas.size()-1){
                        return result;
                    }else {
                        continue;
                    }
                }else {
                    return false;
                }
            }
        }else{
            if(result!=false){
                return result;
            }else{
                return false;
            }
        }
        return result;
    }

    /**
     * 添加附件(已有周报)
     */
    public boolean OnlyAddAttach(HashMap<String,Object> map){
        Integer weeklyId = (Integer) map.get("weeklyId");
        JSONArray jsonArray=JSONArray.fromObject(map.get("cwlas"));
        List<CheckWeeklyListAttach> cwlas=(List<CheckWeeklyListAttach>)JSONArray.toList(jsonArray, CheckWeeklyListAttach.class);
        if(cwlas!=null){
            for (int i = 0; i < cwlas.size() ; i++) {
                cwlas.get(i).setWeeklyId(weeklyId);
                boolean result2 = weeklyListMapper.OnlyAddWeeklyAttach(cwlas.get(i));
                if(result2!=false){
                    if(i==cwlas.size()-1){
                        return result2;
                    }else {
                        continue;
                    }
                }else {
                    return false;
                }
            }
        }else{
            return false;
        }
        return false;
    }
}
