package com.ffcs.itm.web.check.checkSubject.service;

import com.ffcs.itm.web.check.TypeValue.repository.TypeMapper;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKnowListAttach;
import com.ffcs.itm.web.check.checkKnow.entity.CheckKonwList;
import com.ffcs.itm.web.check.checkKnow.repository.CheckKnowListMapper;
import com.ffcs.itm.web.check.checkSubject.entity.CheckSubjectList;
import com.ffcs.itm.web.check.checkSubject.entity.CheckSubjectListAttach;
import com.ffcs.itm.web.check.checkSubject.repository.CheckSubjectListMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CheckSubjectListService {

    @Autowired
    private CheckSubjectListMapper checkSubjectListMapper;
    @Autowired
    private TypeMapper typeMapper;

   public List<CheckSubjectList> getAllList(){
       List<CheckSubjectList> lists = checkSubjectListMapper.selectAllSubjectAndAttach();
       for(int i=0;i<lists.size();i++){
            lists.get(i).setKnowGroupTypeValue(   typeMapper.selectGroupName(lists.get(i).getSubjectGroupType()));
            lists.get(i).setKnowTypeValue(typeMapper.selectListTypeName(lists.get(i).getSubjectType()));
       }
       return lists;
   }

    public CheckSubjectList selectDetail(Integer subjectId){
        CheckSubjectList csl = checkSubjectListMapper.selectDetail(subjectId);
        csl.setKnowGroupTypeValue(   typeMapper.selectGroupName(csl.getSubjectGroupType()));
        csl.setKnowTypeValue(typeMapper.selectListTypeName(csl.getSubjectType()));
        return csl;
   }

   public boolean updateOneSubject(HashMap<String,Object> map){
       JSONObject jsonObject = JSONObject.fromObject(map.get("csl"));
       CheckSubjectList csl = (CheckSubjectList)JSONObject.toBean(jsonObject,CheckSubjectList.class);
       boolean result = checkSubjectListMapper.updateOneSubject(csl);
       return result;
   }

   public boolean insertIntoTogether(HashMap<String,Object> map){
       JSONObject jsonObject = JSONObject.fromObject(map.get("csl"));
       CheckSubjectList csl = (CheckSubjectList)JSONObject.toBean(jsonObject,CheckSubjectList.class);
       boolean result1 = checkSubjectListMapper.CKLaddFirst(csl);
       JSONObject jsonObject2 = new JSONObject();
       JSONArray jsonArray=JSONArray.fromObject(map.get("cslas"));
       List<CheckSubjectListAttach> cslas=(List<CheckSubjectListAttach>)JSONArray.toList(jsonArray,CheckSubjectListAttach.class);
       if(cslas.size()!=0){
           for(int i=0;i<cslas.size();i++){
               CheckSubjectListAttach csla = cslas.get(i);
               checkSubjectListMapper.CKLASecond(csla);
           }
           return result1;
       }else{
           return result1;
       }
   }

    public boolean onlyInsert(HashMap<String,Object> map){
        JSONObject jsonObject = JSONObject.fromObject(map.get("csla"));
        CheckSubjectListAttach csla = (CheckSubjectListAttach)JSONObject.toBean(jsonObject,CheckSubjectListAttach.class);
        boolean result = checkSubjectListMapper.OnlyInsertAttach(csla);
        return result;
    }

    public boolean onlydelOneAttach(HashMap<String,Object> map){
        Integer attachId = (Integer) map.get("attachId");
        return  checkSubjectListMapper.deleteAttachOnly(attachId);
    }

    public boolean delAll(HashMap<String,Object> map){
        Integer subjectId = (Integer) map.get("subjectId");
        boolean result = checkSubjectListMapper.deleteList(subjectId);
        if(result!=false){
           boolean result2 =  checkSubjectListMapper.deleteAll(subjectId);
           return result2;
        }else{
            return false;
        }
    }
}
