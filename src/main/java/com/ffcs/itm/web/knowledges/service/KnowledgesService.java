package com.ffcs.itm.web.knowledges.service;

import com.alibaba.fastjson.JSONObject;
import com.bsnnms.bean.common.StringUtils;
import com.bsnnms.bean.common.security.BASE64Encoder;
import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.common.mybatis.plugin.domain.SimplePageParam;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.service.DateTimeService;
import com.ffcs.itm.web.common.service.SysConfigService;
import com.ffcs.itm.web.knowledges.entity.Attachment;
import com.ffcs.itm.web.knowledges.entity.KeyWord;
import com.ffcs.itm.web.knowledges.entity.Knowledge;
import com.ffcs.itm.web.knowledges.repository.KnowledgesMapper;
import com.ffcs.itm.web.support.PageTool;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class KnowledgesService {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(com.ffcs.itm.web.knowledges.service.KnowledgesService.class);

    @Autowired
    private KnowledgesMapper knowledgesMapper;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private KnowledgeIndexBo knowledgeIndexBo;

    public int hasMenuPri(Long staffId) {
        if (staffId == 1) {
            return 1;
        }
        String managers = getKnowledgeConfig().getString("managers");
        if (!StringUtils.isNull(managers)) {
            return Arrays.asList(managers.split(",")).contains(staffId.toString()) ? 1 : 0;
        } else {
            return 0;
        }
    }

    public Object getMenu() {
        String rootCatalogId = getKnowledgeConfig().getString("rootCatalogId");
        return knowledgesMapper.getMenu(rootCatalogId);
    }

    public Page<Map<String, Object>> getKnowledgesByPage(Map<String, Object> paramMap) {
        int size = 10;
        int page = 0;
        if (paramMap != null && paramMap.get("size") != null && !"".equals(paramMap.get("size"))) {
            size = Integer.valueOf((paramMap.get("size")).toString());
        }
        if (paramMap != null && paramMap.get("page") != null && !"".equals(paramMap.get("page"))) {
            page = Integer.valueOf((paramMap.get("page")).toString());
        }
        SimplePageParam pageParam = new SimplePageParam(page, size);
        return PageTool.getPage(knowledgesMapper.getKnowledgesByPage(paramMap, pageParam), pageParam);
    }

    public Map<String, Object> delKnowledgesById(Long[] knowledgeIds, Staff staffInfo) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (knowledgeIds.length > 0) {
            knowledgesMapper.delKnowledgesById(knowledgeIds);
            //删除记录
            for (Long kId : knowledgeIds) {
                knowledgesMapper.insertKnowledgeManage(kId, staffInfo.getId(), "4SD");
                knowledgesMapper.insertKnowledgeOperate(kId, "删除!(主题：不存)", staffInfo.getId(), "4SD");
            }
        }
        result.put("status", 200);
        result.put("knowledgeIds", knowledgeIds);
        return result;
    }

    public Object getFlowSerial(String type) {
        if (type.equals("KNOW")) {
            return knowledgesMapper.getFlowSerial("KNOW", "KNOW_FLOW_SEQ");
        } else {
            return knowledgesMapper.getFlowSerial("QUESTION", "QUESTION_FLOW_SEQ");
        }
    }

    public Object getKeyWord() {
        return knowledgesMapper.getKeyWord(null);
    }

    public Object savePic(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        JSONObject oResult = null;
        try {
            oResult = doFileUpload(file, request);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        if (oResult.get("filePath") != null && !oResult.getString("filePath").equals("")) {
            result.put("error", 0);
            result.put("title", oResult.get("fileName"));
            result.put("url",
                    "/KnowledgesController/showImg?filePath=" + oResult.get("filePath").toString().replace("\\", "/"));
        } else {
            result.put("error", 1);
            result.put("message", "文件上传失败");
        }
        return result;
    }

    public Object addKnowledge(HttpServletRequest request, Staff staffInfo, Knowledge knowledge,
            MultipartFile[] files) {
        Map<String, Object> result = new HashMap<String, Object>();
        int status = 200;
        Attachment attachment = new Attachment();
        JSONObject oResult = null;
        Long kId = null;
        try {
            knowledgesMapper.addKnowledge(knowledge);
            kId = knowledge.getKnowledgeId();
            knowledgesMapper.insertKnowledgeManage(kId, staffInfo.getId(), "4SA");
            knowledgesMapper.insertKnowledgeOperate(kId, "创建!(主题：" + knowledge.getTitle() + ")", staffInfo.getId(),
                    "4SA");

            //附件
            addAttachMents(files, kId, request);

            //标签
            addKeyWords(knowledge.getTag(), kId);
        } catch (ApplicationException e) {
            status = 400;
        } finally {
            result.put("status", status);
            result.put("knowledgeId", kId);
            return result;
        }

    }

    public Object updKnowledge(HttpServletRequest request, Staff staffInfo, Knowledge knowledge,
            MultipartFile[] files) {
        Map<String, Object> result = new HashMap<String, Object>();
        int status = 200;
        Long kId = knowledge.getKnowledgeId();
        try {
            String state = getKnowledgeState(kId);
            knowledgesMapper.updKnowledge(knowledge);
            if ("4SA".equals(state)) { //已保存的知识
                //更新 operate manage中的时间
                knowledgesMapper.updKnowledgeManage(kId, staffInfo.getId(), "4SA");
                knowledgesMapper.updKnowledgeOperate(kId, staffInfo.getId(), "4SA");
            } else {//已发布的知识 >> 修改
                //加入修改的记录
                knowledgesMapper.insertKnowledgeManage(kId, staffInfo.getId(), "4SH");
                knowledgesMapper.insertKnowledgeOperate(kId, "修改!(主题：" + knowledge.getTitle() + ")", staffInfo.getId(),
                        "4SH");
            }

            //附件 先删除
            delAttachMents(knowledge.getDelAttachIds(), kId);
            addAttachMents(files, kId, request);

            //标签
            knowledgesMapper.delKnKeyWordRel(kId);
            addKeyWords(knowledge.getTag(), kId);
        } catch (ApplicationException e) {
            status = 400;
        } finally {
            result.put("status", status);
            result.put("knowledgeId", kId);
            return result;
        }
    }

    private void delAttachMents(String delAttachIds, Long kId) {
        if (!StringUtils.isNull(delAttachIds)) {
            String[] attachIds = delAttachIds.split(",");
            Long attachId = null;
            for (int i = 0; i < attachIds.length; i++) {
                attachId = Long.parseLong(attachIds[i]);
                knowledgesMapper.delKnowAttachRel(attachId, kId);
                knowledgesMapper.delAttachment(attachId);
            }
        }
    }

    private void addAttachMents(MultipartFile[] files, Long kId, HttpServletRequest request)
            throws ApplicationException {
        JSONObject oResult = null;
        Attachment attachment = new Attachment();
        for (MultipartFile file : files) {
            oResult = doFileUpload(file, request);
            attachment.setAttachPath(oResult.get("relativePath").toString());
            attachment.setAttacheName(oResult.get("fileName").toString());
            knowledgesMapper.addAttachment(attachment);
            knowledgesMapper.addKnowAttachRel(attachment.getAttachId(), kId);
        }
    }

    private void addKeyWords(String tags, Long kId) {
        List<Map<String, Object>> keys = null;
        KeyWord keyWord = null;
        if (!StringUtils.isNull(tags)) {
            String[] tagList = tags.split(",");
            for (int i = 0; i < tagList.length; i++) {
                keys = knowledgesMapper.getKeyWord(tagList[i]);
                if (keys.size() == 0) {
                    keyWord = new KeyWord();
                    keyWord.setKeyWord(tagList[i]);
                    knowledgesMapper.addKeyWord(keyWord);
                    knowledgesMapper.addKnKeyWordRel(keyWord.getKeyWordId(), kId);
                } else {
                    knowledgesMapper.addKnKeyWordRel(Long.valueOf(String.valueOf(keys.get(0).get("KEYWORD_ID"))), kId);
                }
            }
        }
    }

    public void downloadFile(HttpServletResponse response, String path, String fileName) {
        if (!StringUtils.isNull(fileName)) {
            try {
                response.reset();// 清空输出流
                String resultFileName = URLDecoder.decode(fileName, "UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(resultFileName.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
                response.setContentType("application/msexcel");// 定义输出类型
                //输入流：本地文件路径
                DataInputStream in = new DataInputStream(
                        new FileInputStream(new File(sysConfigService.getSysValue("DEFAULT_UPLOAD_PATH") + path)));
                //输出流
                OutputStream out = response.getOutputStream();
                //输出文件
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.reset();
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
                    String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
                    writer.write(data);
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public Object getKnowledgeDetailById(Map<String, Object> params, Long staffId) {
        Map<String, Object> result = new HashMap<String, Object>();

        Long knowledgeId = Long.valueOf(params.get("knowledgeId").toString());

        //知识详情
        result.put("knowledge", knowledgesMapper.getKnowledgeById(knowledgeId, staffId));
        //修改履历
        result.put("his", knowledgesMapper.getOperateHis(knowledgeId));
        //评论
        result.put("comments", getComment(params));
        //分数
        result.put("gradeAvg", knowledgesMapper.getGradeAvg(knowledgeId));

        //知识阅读+1
        knowledgesMapper.addKnowledgeRead(knowledgeId);

        return result;
    }

    public Object getKnowledgesById(Long knowledgeId, Long staffId) {
        return knowledgesMapper.getKnowledgeById(knowledgeId, staffId);
    }

    public Object getComment(Map<String, Object> params) {
        int page = 0;
        int size = 5;
        if (params != null && params.get("size") != null && !"".equals(params.get("size"))) {
            size = Integer.valueOf((params.get("size")).toString());
        }
        if (params != null && params.get("page") != null && !"".equals(params.get("page"))) {
            page = Integer.valueOf((params.get("page")).toString());
        }
        params.put("start", page * size + 1);
        params.put("end", (page + 1) * size);
        List<Map<String, Object>> result = knowledgesMapper.getComment(params);

        Map<String, Object> innerParams = new HashMap<String, Object>();
        innerParams.put("page", 0);
        innerParams.put("size", params.get("innerSize"));
        innerParams.put("staffId", params.get("staffId"));
        innerParams.put("knowledgeId", params.get("knowledgeId"));

        if (result.size() > 0) {
            for (Map<String, Object> row : result) {
                Long gradeId = Long.valueOf(row.get("GRADE_ID").toString());
                innerParams.put("gradeId", gradeId);
                row.put("child", getInnerComment(innerParams));
            }
        }
        return result;
    }

    public Object getInnerComment(Map<String, Object> params) {
        int page = 0;
        int size = 5;
        if (params != null && params.get("size") != null && !"".equals(params.get("size"))) {
            size = Integer.valueOf((params.get("size")).toString());
        }
        if (params != null && params.get("page") != null && !"".equals(params.get("page"))) {
            page = Integer.valueOf((params.get("page")).toString());
        }
        params.put("start", page * size + 1);
        params.put("end", (page + 1) * size);
        return knowledgesMapper.getInnerComment(params);
    }

    public Object addNice(Map<String, Object> params) {
        //判断是否已经点赞
        List<Map<String, Object>> result = knowledgesMapper.getNice(params);
        if (result.size() == 0) {
            knowledgesMapper.addNice(params);
        } else {
            params.put("niceId", result.get(0).get("NICE_ID"));
        }

        return params;
    }

    public void rmNice(Long niceId) {
        knowledgesMapper.rmNice(niceId);
    }

    public void addGrade(Map<String, Object> params) {
        //判断是否已经评论
        List<Map<String, Object>> result = knowledgesMapper.isGraded(params);
        if (result.size() == 0) {
            knowledgesMapper.addGrade(params);
        } else {
            params.put("gradeId", result.get(0).get("GRADE_ID"));
            knowledgesMapper.updGrade(params);
        }

    }

    public Object addComment(Map<String, Object> params) {
        knowledgesMapper.addComment(params);
        return params;
    }

    public void removeComment(Long gradeId) {
        knowledgesMapper.removeComment(gradeId);
    }

    public Object getGradeAvg(Long knowledgeId) {
        return knowledgesMapper.getGradeAvg(knowledgeId);
    }

    public void indexKnowledge(Long knowledgeId, Staff staffInfo) throws ApplicationException {
        Knowledge knowledge = knowledgesMapper.getKnowledgeById(knowledgeId, staffInfo.getId());
        knowledgeIndexBo.updateDocument("", knowledgeId.toString(),
                knowledge.getCatalogName() + ";" + knowledge.getContent(), knowledge, staffInfo);
    }

    public void rmKnowledgeIndex(Long knowledgeId, Staff staffInfo) throws ApplicationException {
        Knowledge knowledge = knowledgesMapper.getKnowledgeById(knowledgeId, staffInfo.getId());
        knowledgeIndexBo.moveDocument("publish_index_directory", "delete_index_directory", null, knowledgeId.toString(),
                staffInfo, knowledge);
    }

    public Object searchKnowledges(Map<String, Object> paramMap)
            throws ApplicationException, UnsupportedEncodingException {
        KnowledgeRpcClient rpcClient = KnowledgeRpcClient.getInstance(getKnowledgeConfig().getString("spider"));
        Map<String, Object> result = new HashMap<String, Object>();

        String queryString = paramMap.get("queryString").toString(); //查询输入框
        String page = paramMap.get("page").toString();
        String size = paramMap.get("size").toString();
        Object catalogId = paramMap.get("catalogId"); //目录限制
        String limitString = "";
        if (catalogId != null) {
            String catalogIds = knowledgesMapper.getCatalogIds(Long.valueOf(catalogId.toString()));
            if (!StringUtils.isNull(catalogIds)) {
                limitString = "+category:(" + catalogIds + ")";
            } else {
                limitString = "+category:(" + catalogId.toString() + ")";
            }
        }
        Object sort = paramMap.get("sortField"); //排序字段
        String sortField = (sort == null ? "" : sort.toString());
        Object reverse = paramMap.get("isReverse"); //排序正反序
        String isReverse = (reverse == null ? "TRUE" : reverse.toString());
        if (!StringUtils.isNull(queryString)) {
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] bs = queryString.getBytes("utf-8");
            queryString = encoder.encode(bs);
        }

        Vector vctParams = new Vector();
        vctParams.addElement("publish_index_directory");
        vctParams.addElement("title,content,justKeyword,author,flowSerial");
        vctParams.addElement(queryString);
        vctParams.addElement(" +module:knowledge +url:workshop* -state:4SA " + limitString);
        vctParams.addElement(sortField);
        vctParams.addElement(isReverse);
        vctParams.addElement(Integer.parseInt(page));
        vctParams.addElement(Integer.parseInt(size));

        boolean ret = rpcClient.execute("SearchRpcHandleNew.SearchDocument", vctParams);
        Map tab = (Map) rpcClient.getResult();
        if (!ret || (tab.containsKey("faultString") && tab.containsKey("faultCode"))) { //有异常返回
            String strErrorMsg = (String) tab.get("faultString");
            String strErrorCode = (String) tab.get("faultCode");
            throw new ApplicationException(strErrorCode + ":" + strErrorMsg);
        } else {
            result.put("page", page);
            result.put("size", size);
            result.put("data", (String) tab.get("result"));
            result.put("length", tab.get("length"));
        }
        return result;
    }

    public void exportExcel(HttpServletResponse response, List<Long> knowledgeIds, String type) throws IOException {
        List<List<String>> result = Lists.newArrayList();
        String[] titles = null;
        String[] fieldNames = null;
        if (type.equals("KNOW")) {
            titles = new String[]{"知识单号", "知识标题", "作者", "阅读/评论", "发布日期"};
            fieldNames = new String[]{"FLOW_SERIAL", "TITLE", "STAFF_NAME", "READ_COMMENT", "CREATE_DATE"};
        } else {
            titles = new String[]{"问题单号", "问题标题", "类型", "优先级", "到期日"};
            fieldNames = new String[]{"FLOW_SERIAL", "TITLE", "TYPE_NAME", "RANK_NAME", "EXPIRE_DATE"};
        }
        List<Map<String, Object>> contents = knowledgesMapper.getExportContent(knowledgeIds);
        for (Map<String, Object> content : contents) {
            List<String> row = new ArrayList<String>();
            for (String name : fieldNames) {
                row.add(content.get(name) == null ? "" : content.get(name).toString());
            }
            result.add(row);
        }
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = formatter.format(date);
        new KnowledgeExcelExporter().export(response, ((type.equals("KNOW") ? "知识" : "问题") + "导出" + currentTime),
                titles, result);
    }

    /*目录管理*/
    public Object getMenuPri(Long staffId) {
        String rootCatalogId = getKnowledgeConfig().getString("rootCatalogId");
        if (staffId == 1) {
            return knowledgesMapper.getMenu(rootCatalogId);
        }
        return knowledgesMapper.getMenuPri(rootCatalogId, staffId);
    }

    public Object getMenuType(String type) {
        String rootCatalogId = getKnowledgeConfig().getString("rootCatalogId");
        return knowledgesMapper.getMenuType(rootCatalogId, type);
    }

    public Object delCatalog(Long catalogId) {
        Map<String, Object> result = new HashMap<String, Object>();
        int status = 200;
        String message = "删除成功";
        if (knowledgesMapper.hasChildCatalog(catalogId) > 0) {
            status = 400;
            message = "该节点底下有文件夹/页面，不能删除!";
        } else if (knowledgesMapper.hasKnowledge(catalogId) > 0) {
            status = 400;
            message = "该页面下有对应知识/问题，不能删除!";
        } else {
            knowledgesMapper.delCatalog(catalogId);
        }
        result.put("status", status);
        result.put("message", message);
        return result;
    }

    public void editCatalog(Long catalogId, String newName) {
        knowledgesMapper.updCatalog(catalogId, newName);
    }

    public Object addCatalog(Map<String, Object> params) {
        knowledgesMapper.addCatalog(params);
        return params;
    }

    public void dragCatalog(Map<String, Object> params) {
        knowledgesMapper.dragCatalog(params);
    }

    private JSONObject getKnowledgeConfig() {
        return JSONObject.parseObject(knowledgesMapper.getSysConfig("NEW_KNOWLEDGE_CONFIG"));
    }

    private String getKnowledgeState(Long knowledgeId) {
        return knowledgesMapper.getKnowledgeState(knowledgeId);
    }

    public JSONObject doFileUpload(MultipartFile file, HttpServletRequest request) throws ApplicationException {
        Date systemDate = dateTimeService.getDBCurrentDate("yyyy-MM");
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM");
        String beginDate = dateformat.format(systemDate);

        int result = 1;
        JSONObject oResult = new JSONObject();

        String fileName = null;
        String[] fileInfo = null;
        String uploadPath = sysConfigService.getSysValue("DEFAULT_UPLOAD_PATH") + "itm-knowledges/" + beginDate + "/";

        if (file != null) {
            fileName = file.getOriginalFilename();
            fileInfo = fileName.split("\\.");
            fileName = UUID.randomUUID() + "." + fileInfo[1];
            File targetFile = new File(uploadPath, fileName);

            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
                result = -1;
            }
        }
        oResult.put("result", result);
        oResult.put("filePath", uploadPath + fileName);
        oResult.put("relativePath", "/itm-knowledges/" + beginDate + "/" + fileName);
        oResult.put("fileName", file.getOriginalFilename());
        oResult.put("fileType", fileInfo[1]);
        return oResult;
    }

    public Object checkState(Long knowledgeId) {
        Map<String, Object> result = new HashMap<String, Object>();
        int status = 200;
        String message = "该知识/问题存在";
        String state = knowledgesMapper.getState(knowledgeId);
        if (state == null || state.equals("4SD")) {
            status = 400;
            message = "该知识/问题不存在";
        }
        result.put("status", status);
        result.put("message", message);
        return result;
    }
}