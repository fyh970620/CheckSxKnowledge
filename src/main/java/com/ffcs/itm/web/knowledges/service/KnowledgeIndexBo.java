package com.ffcs.itm.web.knowledges.service;

import com.alibaba.fastjson.JSONObject;
import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.common.service.SysConfigService;
import com.ffcs.itm.web.knowledges.entity.Attachment;
import com.ffcs.itm.web.knowledges.entity.KeyWord;
import com.ffcs.itm.web.knowledges.entity.Knowledge;
import com.ffcs.itm.web.knowledges.repository.KnowledgesMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@Component
public class KnowledgeIndexBo {

    private static final Logger logger = LoggerFactory.getLogger(KnowledgeIndexBo.class);


    @Autowired
    private KnowledgesMapper knowledgesMapper;

    @Autowired
    private SysConfigService sysConfigService;

    //(1). 功能：审核、删除、恢复索引 (索引的移动和创建)
    public void moveDocument(String fromDirectory, String toDirectory, String oldModule, String oldMainTableId,
            Staff staffInfo, Knowledge knowledge) throws ApplicationException {
        KnowledgeRpcClient rpcClient = KnowledgeRpcClient.getInstance(getKnowledgeConfig().getString("spider"));
        Vector vctParams = new Vector();
        vctParams.addElement(fromDirectory);
        vctParams.addElement(toDirectory);
        vctParams.addElement(oldModule == null ? "" : oldModule);
        vctParams.addElement(oldMainTableId);
        vctParams.addElement(staffInfo.getStaffName());
        Hashtable tabKnowledge = null;
        if (knowledge != null) {
            tabKnowledge = this.putKnowledgeInfo(knowledge, staffInfo);
        } else {
            tabKnowledge.put("knowledgeId", "-1");
        }
        vctParams.addElement(tabKnowledge);
        boolean result = rpcClient.execute("IndexRpcHandle.moveKnowledgeDocument", vctParams);
        if (!result) {
            this.throwError(rpcClient);
        }
    }

    //(2). 功能：更新或者添加索引 (索引的更新或创建)
    public void updateDocument(String oldModule, String oldMainTableId, String indexContent, Knowledge knowledge,
            Staff staffInfo) throws ApplicationException {
        KnowledgeRpcClient rpcClient = KnowledgeRpcClient.getInstance(getKnowledgeConfig().getString("spider"));
        Vector vctParams = new Vector();
        vctParams.addElement(oldModule == null ? "" : oldModule);
        vctParams.addElement(oldMainTableId);
        vctParams.addElement(indexContent);
        vctParams.addElement(staffInfo.getStaffName());
        Hashtable tabKnowledge = this.putKnowledgeInfo(knowledge, staffInfo);
        vctParams.addElement(tabKnowledge);
        logger.info(vctParams.toString());
        boolean result = rpcClient.execute("IndexRpcHandle.updateKnowledgeDocument", vctParams);
        if (!result) {
            this.throwError(rpcClient);
        }
    }

    //(3). 功能：删除附件
    public void removeAttachment(String indexDirectory, String knowledgeId, Attachment attach)
            throws ApplicationException {
        KnowledgeRpcClient rpcClient = KnowledgeRpcClient.getInstance(getKnowledgeConfig().getString("spider"));
        Vector vctParams = new Vector();
        vctParams.addElement(indexDirectory);
        Hashtable tabAttach = new Hashtable();
        tabAttach.put("knowledgeid", knowledgeId.toString());
        tabAttach.put("attachid", attach.getAttachId());
        tabAttach.put("filename", attach.getAttachName());
        tabAttach.put("path", attach.getAttachPath());
        tabAttach.put("uploadpath", sysConfigService.getSysValue("DEFAULT_UPLOAD_PATH"));
        vctParams.addElement(tabAttach);
        boolean result = rpcClient.execute("IndexRpcHandle.removeAttachment", vctParams);
        if (!result) {
            this.throwError(rpcClient);
        }
    }

    private Hashtable putKnowledgeInfo(Knowledge knowledge, Staff staffInfo) {
        Hashtable tabKnowledge = new Hashtable();
        List<KeyWord> keyWords = knowledge.getKeyWords();
        String[] keyArr = new String[keyWords.size()];
        for (int i = 0; i < keyWords.size(); i++) {
            keyArr[i] = keyWords.get(i).getKeyWord();
        }

        tabKnowledge.put("knowledgeId", knowledge.getKnowledgeId().toString());
        tabKnowledge.put("hasAttach", knowledge.getAttachments().size() == 0 ? "NOT" : "YES");
        tabKnowledge.put("subject", knowledge.getTitle() == null ? "" : knowledge.getTitle());
        tabKnowledge.put("catalogId", knowledge.getCatalogId() == null ? "" : knowledge.getCatalogId().toString());
        tabKnowledge.put("catalogName", knowledge.getCatalogName() == null ? "" : knowledge.getCatalogName());
        tabKnowledge.put("state", knowledge.getState() == null ? "" : knowledge.getState());
        tabKnowledge.put("keywords", knowledge.getKeyWords().size() == 0 ? new String[]{""} : keyArr);
        tabKnowledge.put("sourcetype", knowledge.getSourceType() == null ? "" : knowledge.getSourceType());
        tabKnowledge.put("content", knowledge.getContents() == null ? "" : knowledge.getContents());
        tabKnowledge.put("simpleContent", knowledge.getContent() == null ? "" : knowledge.getContent());
        tabKnowledge.put("uploadpath", sysConfigService.getSysValue("DEFAULT_UPLOAD_PATH"));
        tabKnowledge.put("readCount", knowledge.getHitCount() + "");
        tabKnowledge.put("justKeyword", knowledge.getKeyWords().size() == 0 ? "" : StringUtils.join(keyArr, ","));
        tabKnowledge.put("authorId", staffInfo.getId() + "");
        tabKnowledge.put("flowSerial", knowledge.getFlowSerial());
        tabKnowledge.put("creationDate", knowledge.getCreateDate());

        if (knowledge.getAttachments().size() > 0) {
            Vector vctAttach = new Vector();
            List<Attachment> attach = knowledge.getAttachments();
            for (int i = 0; i < attach.size(); i++) {
                if (attach.get(i) != null) {
                    Hashtable tabAttach = new Hashtable();
                    tabAttach.put("attachid", attach.get(i).getAttachId().toString());
                    tabAttach.put("filename", attach.get(i).getAttachName());
                    tabAttach.put("path", attach.get(i).getAttachPath());
                    vctAttach.addElement(tabAttach);
                }
            }
            tabKnowledge.put("attachment", vctAttach);
        }
        return tabKnowledge;
    }

    private JSONObject getKnowledgeConfig() {
        return JSONObject.parseObject(knowledgesMapper.getSysConfig("NEW_KNOWLEDGE_CONFIG"));
    }

    private void throwError(KnowledgeRpcClient rpcClient) throws ApplicationException {
        Map tab = (Map) rpcClient.getResult();
        String strErrorMsg = (String) tab.get("faultString");
        String strErrorCode = (String) tab.get("faultCode");
        throw new ApplicationException(strErrorCode + ":" + strErrorMsg);
    }
}
