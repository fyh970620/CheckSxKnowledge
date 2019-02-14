package com.ffcs.itm.web.check.weeklyList.service;

import com.ffcs.itm.web.check.utils.BaseResponse;
import com.ffcs.itm.web.check.weeklyList.entity.CheckWeeklyListComment;
import com.ffcs.itm.web.check.weeklyList.repository.WeeklyListCommentsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeeklyListCommentsService {

    @Autowired
    private WeeklyListCommentsMapper weeklyListCommentsMapper;

    public PageInfo<CheckWeeklyListComment> getAllCheckWeeklyById(Integer pages, Integer pageSize, Integer weeklyId){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyListComment> list = weeklyListCommentsMapper.selectAllCommentsInId(weeklyId);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        for(int i=0;i<list.size();i++){
            List<CheckWeeklyListComment> lists = weeklyListCommentsMapper.selectAllCommentsChridren2(weeklyId,list.get(i).getCommentId());
            list.get(i).setTime(sdf.format(list.get(i).getCommentDate()));
            list.get(i).setStaff(weeklyListCommentsMapper.selectDetailStaff(list.get(i).getCommentStaffId()));
            list.get(i).setOd(0);
            for(int j=0;j<lists.size();j++){
                lists.get(j).setTime(sdf.format(lists.get(j).getCommentDate()));
                if(lists.get(j).getCommentParentId()==0){
                    lists.get(j).setOd(0);
                    lists.get(j).setStaff(weeklyListCommentsMapper.selectDetailStaff(lists.get(j).getCommentStaffId()));
                }else{
                    lists.get(j).setOd(0);
                    lists.get(j).setParentUser(weeklyListCommentsMapper.selectDetailStaff( weeklyListCommentsMapper.selectByParentId(lists.get(j).getCommentParentId()).getCommentStaffId()));
                    System.out.println(weeklyListCommentsMapper.selectByParentId(lists.get(j).getCommentParentId()));
                    lists.get(j).setStaff(weeklyListCommentsMapper.selectDetailStaff(lists.get(j).getCommentStaffId()));
                }

            }
            list.get(i).setChildren(lists);
        }
        PageInfo<CheckWeeklyListComment> pageInfo = new PageInfo<CheckWeeklyListComment>(list);
        return pageInfo;
    }

  /*  public PageInfo<CheckWeeklyListComment> getAllCheckWeeklyById(Integer pages, Integer pageSize, Integer weeklyId){
        PageHelper.startPage(pages,pageSize);
        List<CheckWeeklyListComment> list = weeklyListCommentsMapper.selectAllCommentsInId(weeklyId);
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        for(int i=0;i<list.size();i++){
            Integer rootNode = list.get(i).getCommentId();
            list.get(i).setTime(sdf.format(list.get(i).getCommentDate()));
            list.get(i).setStaff(weeklyListCommentsMapper.selectDetailStaff(list.get(i).getCommentStaffId()));
            List<CheckWeeklyListComment> children = weeklyListCommentsMapper.selectAllCommentsChridren(weeklyId,rootNode,rootNode);
            getTreeDate(children,weeklyId);
            list.get(i).setChildren(children);
        }
        PageInfo<CheckWeeklyListComment> cwlcs = new PageInfo<CheckWeeklyListComment>(list);
        return cwlcs;
    }
    *//**
     *
     * @param
     * @return
     *//*
    public List<CheckWeeklyListComment> getTreeDate(List<CheckWeeklyListComment>children,Integer weeklyId) {
        //获取所有的根节点
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        List<CheckWeeklyListComment> newChildren = new ArrayList<CheckWeeklyListComment>();
        for (int i = 0; i < children.size(); i++) {
            Integer NewRootNode = children.get(i).getCommentId();
            children.get(i).setTime(sdf.format(children.get(i).getCommentDate()));
            children.get(i).setStaff(weeklyListCommentsMapper.selectDetailStaff(children.get(i).getCommentStaffId()));
            newChildren = weeklyListCommentsMapper.selectAllCommentsChridren(weeklyId, NewRootNode, NewRootNode);
            if(newChildren!=null){
                children.get(i).setChildren(newChildren);
                getTreeDate(newChildren,weeklyId);
            }else{
                return children;
            }
        }
        return children;
    }
*/
    public boolean insertTopComments(HashMap<String,Object> map){
        JSONObject jsonObject=JSONObject.fromObject(map.get("cwlc"));
        CheckWeeklyListComment cwlc=(CheckWeeklyListComment) JSONObject.toBean(jsonObject, CheckWeeklyListComment.class);
        boolean result = weeklyListCommentsMapper.insertTopComment(cwlc);
        if(result!=false){
            return true;
        }else{
            return false;
        }
    }

    public boolean insertBackComments(HashMap<String,Object> map){
        JSONObject jsonObject=JSONObject.fromObject(map.get("cwlc"));
        CheckWeeklyListComment cwlc=(CheckWeeklyListComment) JSONObject.toBean(jsonObject, CheckWeeklyListComment.class);
        boolean result = weeklyListCommentsMapper.insertCommentBack(cwlc);
        if(result!=false){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 评论列表删除评论
     * @param
     * @return
     */
    public BaseResponse delOneCommentFromList(HashMap<String,Object> map){
        Integer weeklyId = (Integer) map.get("weeklyId");
        Integer rootNode = (Integer) map.get("commentId");
        BaseResponse bs = new BaseResponse();
        List<CheckWeeklyListComment> children =weeklyListCommentsMapper.selectAllCommentsChridren(weeklyId,rootNode,rootNode);
        boolean result =  weeklyListCommentsMapper.delOneComments(rootNode);
        if(children.size()!=0){
            getTreeDate2(children,weeklyId);
            if(getTreeDate2(children,weeklyId)==null){
                bs.setMsg("删除成功");
                bs.setCode(1);
                return bs;
            }else {
                bs.setMsg("删除失败");
                bs.setCode(0);
                return bs;
            }
        }else{
            if(result != false){
                bs.setMsg("删除成功");
                bs.setCode(1);
                return bs;
            }else{
                bs.setMsg("删除失败");
                bs.setCode(0);
                return bs;
            }

        }


    }
    public List<CheckWeeklyListComment> getTreeDate2(List<CheckWeeklyListComment>children,Integer weeklyId) {
        //获取所有的根节点
        List<CheckWeeklyListComment> newChildren = new ArrayList<CheckWeeklyListComment>();
        for (int i = 0; i < children.size(); i++) {
            Integer NewRootNode = children.get(i).getCommentId();
            newChildren = weeklyListCommentsMapper.selectAllCommentsChridren(weeklyId, NewRootNode, NewRootNode);
            weeklyListCommentsMapper.delOneComments(NewRootNode);
            if(newChildren!=null){
                getTreeDate2(newChildren,weeklyId);
            }else{
                return newChildren;
            }
        }
        return children;
    }

    /**
     * 删除周报时删除周报下所有的评论
     */
    public boolean delCommentsByWeekly(Integer weeklyId){
        boolean result = weeklyListCommentsMapper.delCommentsByWeekly(weeklyId);
        return result;
    }

    /**
     * 好评率
     */
    public BaseResponse selectCommentAvgCount(Integer weeklyId){
        BaseResponse bs = new BaseResponse();
        Double count = weeklyListCommentsMapper.selectOneAvg(weeklyId);
        System.out.println((double) (Math.round(count)/20.0));
        bs.setCode(1);
        bs.setMsg("平均数查询");
        Map<String,Double> map = new HashMap<String,Double>();
        map.put("avg",(double) (Math.round(count)/20.0));
        bs.setResponseBody(map);
        return bs;


    }

}
