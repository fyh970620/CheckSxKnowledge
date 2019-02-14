package com.ffcs.itm.web.knowledges.repository;

import com.ffcs.itm.common.mybatis.plugin.domain.PageParam;
import com.ffcs.itm.web.knowledges.entity.Attachment;
import com.ffcs.itm.web.knowledges.entity.KeyWord;
import com.ffcs.itm.web.knowledges.entity.Knowledge;
import com.ffcs.itm.web.respository.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface KnowledgesMapper {
    String getSysConfig(String sysVar);

    String getKnowledgeState(Long knowledgeId);

    Map<String, Object> getFlowSerial(@Param("name") String name, @Param("seqName") String seqName);

    List<Map<String, Object>> getMenu(@Param("catalogId") String catalogId);

    List<Map<String, Object>> getKnowledgesByPage(@Param("params") Map<String, Object> params, PageParam pager);

    void delKnowledgesById(@Param("knowledgeIds") Long[] knowledgeIds);

    /*新增 修改知识*/
    void addKnowledge(Knowledge knowledge);

    void updKnowledge(Knowledge knowledge);

    void insertKnowledgeManage(@Param("knowledgeId") Long knowledgeId, @Param("staffId") Long staffId,
            @Param("type") String type);

    void insertKnowledgeOperate(@Param("knowledgeId") Long knowledgeId, @Param("content") String content,
            @Param("staffId") Long staffId, @Param("type") String type);

    void updKnowledgeManage(@Param("knowledgeId") Long knowledgeId, @Param("staffId") Long staffId,
            @Param("type") String type);

    void updKnowledgeOperate(@Param("knowledgeId") Long knowledgeId, @Param("staffId") Long staffId,
            @Param("type") String type);

    void addAttachment(Attachment attachment);

    void addKnowAttachRel(@Param("attachId") Long attachId, @Param("knowledgeId") Long knowledgeId);

    void delAttachment(Long attachId);

    void delKnowAttachRel(@Param("attachId") Long attachId, @Param("knowledgeId") Long knowledgeId);

    void addKeyWord(KeyWord keyWord);

    void addKnKeyWordRel(@Param("keyWordId") Long keyWordId, @Param("knowledgeId") Long knowledgeId);

    void delKnKeyWordRel(Long knowledgeId);

    /*查看知识详情*/
    void addKnowledgeRead(Long knowledgeId);

    Knowledge getKnowledgeById(@Param("knowledgeId") Long knowledgeId, @Param("staffId") Long staffId);

    List<Map<String, Object>> getComment(@Param("params") Map<String, Object> params);

    List<Map<String, Object>> getInnerComment(@Param("params") Map<String, Object> params);

    Map<String, Object> getGradeAvg(Long knowledgeId);

    List<Map<String, Object>> getOperateHis(Long knowledgeId);

    List<Map<String, Object>> getNice(@Param("params") Map<String, Object> params);

    void addNice(@Param("params") Map<String, Object> params);

    void rmNice(Long niceId);

    List<Map<String, Object>> isGraded(@Param("params") Map<String, Object> params);

    void addGrade(@Param("params") Map<String, Object> params);

    void updGrade(@Param("params") Map<String, Object> params);

    void addComment(@Param("params") Map<String, Object> params);

    void removeComment(Long gradeId);

    List<Map<String, Object>> getKeyWord(@Param("keyWord") String keyWord);

    List<Map<String, Object>> getExportContent(@Param("knowledgeIds") List<Long> knowledgeIds);

    /*目录管理*/
    List<Map<String, Object>> getMenuPri(@Param("catalogId") String catalogId, @Param("staffId") Long staffId);

    List<Map<String, Object>> getMenuType(@Param("catalogId") String catalogId, @Param("type") String type);

    Integer hasChildCatalog(Long catalogId);

    Integer hasKnowledge(Long catalogId);

    void delCatalog(Long catalogId);

    void updCatalog(@Param("catalogId") Long catalogId, @Param("name") String name);

    void addCatalog(@Param("params") Map<String, Object> params);

    void dragCatalog(@Param("params") Map<String, Object> params);

    String getCatalogIds(Long catalogId);

    String getState(Long knowledgeId);
}