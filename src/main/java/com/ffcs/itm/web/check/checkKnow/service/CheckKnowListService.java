package com.ffcs.itm.web.check.checkKnow.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKnowListAttach;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.check.checkKnow.repository.CheckKnowListMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CheckKnowListService {

    @Autowired
    private CheckKnowListMapper checkKnowListMapper;
    @Autowired
    private TypeMapper typeMapper;

    /**
     * PageHelper操作数据
     * @param pages
     * @param pageSize
     * @return
     * **注:将查出来的ATTACH_ID & ckl.know_id 放到隐藏hidden中，可以不显示，但需要保存命名一致，方便删除
     */
    public PageInfo<CheckKonwList> getListCheckKnow(Integer pages, Integer pageSize, String knowGroupType){
        PageHelper.startPage(pages,pageSize);
        List<CheckKonwList> lists = checkKnowListMapper.selectCheckKnows(knowGroupType);
        PageInfo<CheckKonwList> ckls = new PageInfo<CheckKonwList>(lists);
        for (int i = 0; i < ckls.getList().size() ; i++) {
            CheckKonwList ckl =  ckls.getList().get(i);
            ckl.setKnowTypeValue(typeMapper.selectListTypeName(ckl.getKnowType()));
            ckl.setKnowGroupTypeValue(typeMapper.selectGroupName(knowGroupType));
            if(checkKnowListMapper.selectListCheckKnowAttach(ckl.getKnowId())!=null){
                ckl.setCklas(checkKnowListMapper.selectListCheckKnowAttach(ckl.getKnowId()));
            }else{
                continue;
            }

        }
        return ckls;
    }

    /**
     * 新增数据 新课题
     */
     public boolean AddOneAttach(HashMap<String,Object> map){
         JSONObject jsonObject=JSONObject.fromObject(map.get("ckl"));
         CheckKonwList ckl=(CheckKonwList)JSONObject.toBean(jsonObject, CheckKonwList.class);
         boolean result = checkKnowListMapper.CKLaddFirst(ckl);
         JSONObject jsonObject2 = new JSONObject();
         JSONArray jsonArray=JSONArray.fromObject(map.get("cklas"));
         List<CheckKnowListAttach> cklas=(List<CheckKnowListAttach>)JSONArray.toList(jsonArray,CheckKnowListAttach.class);
         if(cklas!=null){
             for(int i=0;i<cklas.size();i++){
                 /*System.out.println("===>" + cklas.get(i).toString());*/
                 Integer result2 = checkKnowListMapper.CKLASecond(cklas.get(i));
                 if(result2>0 & result!=false){
                     if(i==cklas.size()-1){
                         return result;
                     }else{
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
                 return result;
             }
         }
         return result;
     }

    /**
     * 删除
     */
    public boolean deleteOneCheckKnow(JSONObject object){
        Integer knowId = object.getInt("knowId");
        boolean result =checkKnowListMapper.deleteOneCkla(knowId);
        if(result!=false){
            boolean result2 = checkKnowListMapper.deleteOneCkl(knowId);
            if(result2!=false){
                return true;
            }else{
                return false;
            }
        }else {
            //没有附件
            boolean result2 = checkKnowListMapper.deleteOneCkl(knowId);
            if (result2 != false) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * 只删除附件
     */
    public boolean delOnlyAttach(HashMap<String,Object> map){
        Integer attachId = (Integer)map.get("attachId");
        boolean result = checkKnowListMapper.delOnlyAttach(attachId);
        return result;
    }
    /**
     * 更新
     */
    public boolean UpdateCKLandCKLA(HashMap<String,Object> map){
        JSONObject jsonObject=JSONObject.fromObject(map.get("ckl"));
        CheckKonwList ckl=(CheckKonwList)JSONObject.toBean(jsonObject, CheckKonwList.class);
        boolean result = checkKnowListMapper.updateOneCKL(ckl);
        if(result!=false){
            return result;
        }else{
            return false;
        }
    }

    /**
     * 额外添加附件
     */
    public boolean addExtraAttach(HashMap<String,Object> map){
        System.out.println("=================>" +map.get("knowId"));
        Integer knowId =(Integer)map.get("knowId");
        JSONArray jsonArray=JSONArray.fromObject(map.get("cklas"));
        List<CheckKnowListAttach> cklas=(List<CheckKnowListAttach>)JSONArray.toList(jsonArray, CheckKnowListAttach.class);
        if(cklas!=null){
            for (int i = 0; i < cklas.size() ; i++) {
                cklas.get(i).setKnowId(knowId);
                boolean result2 = checkKnowListMapper.onlyAddCheckKnow(cklas.get(i));
                if(result2!=false){
                    if(i==cklas.size()-1){
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
