package com.ffcs.itm.web.knowledges.controllers;

import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.web.basic.entity.Staff;
import com.ffcs.itm.web.basic.service.CodeService;
import com.ffcs.itm.web.common.service.DomainService;
import com.ffcs.itm.web.knowledges.entity.Knowledge;
import com.ffcs.itm.web.knowledges.service.KnowledgesService;
import com.ffcs.itm.web.support.SessionInfo;
import net.sf.json.JSONObject;
import org.jfree.chart.servlet.ServletUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/KnowledgesController")
public class KnowledgesController {

    @Autowired
    private KnowledgesService knowledgesService;

    @Autowired
    private DomainService domainService;

    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    //@ResponseBody
    //public Object toIndex() {
    public String toIndex(ModelMap modelMap) {
        Staff staffInfo = SessionInfo.getStaff();
        modelMap.addAttribute("staffInfo", staffInfo);
        modelMap.addAttribute("MENU_PRIVILEGE", knowledgesService.hasMenuPri(staffInfo.getId()));
        modelMap.addAttribute("KNOW_CATALOG", knowledgesService.getMenu());
        modelMap.addAttribute("KNOW_TYPE", codeService.getCodeList("GD_TITLE_TYPE"));
        modelMap.addAttribute("KNOW_LABEL", knowledgesService.getKeyWord());
        modelMap.addAttribute("KNOW_RANK", domainService.getListByCode("DOMAIN_KNOWLEDGE_RANK"));

        /*Staff staffInfo = SessionInfo.getStaff();
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("staffInfo", staffInfo);
        result.put("MENU_PRIVILEGE", knowledgesService.hasMenuPri(staffInfo.getId()));
        result.put("KNOW_CATALOG", knowledgesService.getMenu());
        result.put("KNOW_LABEL", knowledgesService.getKeyWord());
        result.put("KNOW_CATALOG_TYPE", codeService.getCodeList("KNOWLEDGE_CATALOG_TYPE"));
        result.put("KNOW_RANK", domainService.getListByCode("DOMAIN_KNOWLEDGE_RANK"));*/
        //return result;
        return "knowledges/index";
    }

    /*获取知识问题 分页
    * state 4SA 草稿箱
    * */
    @RequestMapping(value = "/getKnowledgesByPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getKnowledgesByPage(@RequestBody Map<String, Object> paramMap) {
        Staff staffInfo = SessionInfo.getStaff();
        paramMap.put("staffId", staffInfo.getId());
        return knowledgesService.getKnowledgesByPage(paramMap);
    }

    /*获取知识详情*/
    @RequestMapping(value = "/getKnowledgeDetailById", method = RequestMethod.POST)
    @ResponseBody
    public Object getKnowledgeDetailById(@RequestBody Map<String, Object> params) {
        Staff staffInfo = SessionInfo.getStaff();
        params.put("staffId", staffInfo.getId());
        return knowledgesService.getKnowledgeDetailById(params, staffInfo.getId());
    }

    /*修改界面知识*/
    @RequestMapping(value = "/getKnowledgesById", method = RequestMethod.GET)
    @ResponseBody
    public Object getKnowledgesById(Long knowledgeId) {
        Staff staffInfo = SessionInfo.getStaff();
        return knowledgesService.getKnowledgesById(knowledgeId, staffInfo.getId());
    }

    /*获取评论*/
    @RequestMapping(value = "/getComment", method = RequestMethod.POST)
    @ResponseBody
    public Object getComment(@RequestBody Map<String, Object> params) {
        Staff staffInfo = SessionInfo.getStaff();
        params.put("staffId", staffInfo.getId());
        return knowledgesService.getComment(params);
    }

    /*获取子评论*/
    @RequestMapping(value = "/getInnerComment", method = RequestMethod.POST)
    @ResponseBody
    public Object getInnerComment(@RequestBody Map<String, Object> params) {
        Staff staffInfo = SessionInfo.getStaff();
        params.put("staffId", staffInfo.getId());
        return knowledgesService.getInnerComment(params);
    }

    /*点赞*/
    @RequestMapping(value = "/addNice", method = RequestMethod.POST)
    @ResponseBody
    public Object addNice(@RequestBody Map<String, Object> params) {
        Staff staffInfo = SessionInfo.getStaff();
        params.put("staffId", staffInfo.getId());
        return knowledgesService.addNice(params);
    }

    /*取消点赞*/
    @RequestMapping(value = "/rmNice")
    @ResponseStatus(HttpStatus.OK)
    public void rmNice(Long niceId) {
        knowledgesService.rmNice(niceId);
    }

    /*评分*/
    @RequestMapping(value = "/addGrade")
    @ResponseStatus(HttpStatus.OK)
    public void addGrade(Long knowledgeId, Integer grade) {
        Staff staffInfo = SessionInfo.getStaff();
        Map<String, Object> params = new HashMap<>();
        params.put("knowledgeId", knowledgeId);
        params.put("staffId", staffInfo.getId());
        params.put("grade", grade);
        knowledgesService.addGrade(params);
    }

    /*评论知识*/
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Object addComment(@RequestBody Map<String, Object> params) {
        Staff staffInfo = SessionInfo.getStaff();
        params.put("staffId", staffInfo.getId());
        return knowledgesService.addComment(params);
    }

    /*删除评论*/
    @RequestMapping(value = "/removeComment", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void removeComment(Long gradeId) {
        knowledgesService.removeComment(gradeId);
    }

    /*获取知识平均分*/
    @RequestMapping(value = "/getGradeAvg", method = RequestMethod.GET)
    @ResponseBody
    public Object getGradeAvg(Long knowledgeId) {
        return knowledgesService.getGradeAvg(knowledgeId);
    }

    /*新增 知识问题单号
     * KNOW      知识
     * QUESTION  问题 */
    @RequestMapping(value = "/getFlowSerial", method = RequestMethod.GET)
    @ResponseBody
    public Object getFlowSerial(String type) {
        return knowledgesService.getFlowSerial(type);
    }

    /*知识中的图片*/
    @RequestMapping(value = "/savePic", method = RequestMethod.POST)
    public void savePic(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        Staff staffInfo = SessionInfo.getStaff();
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(knowledgesService.savePic(request, file)).toString());
        out.flush();
    }

    /*展示图片*/
    @RequestMapping(value = "/showImg")
    public void showImg(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        ServletUtilities.sendTempFile(file, response);
    }

    /*文件下载*/
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(String path, String fileName, HttpServletResponse response) {
        knowledgesService.downloadFile(response, path, fileName);
    }

    /*文件下载批量*/
    @RequestMapping(value = "/downloadFiles")
    public void downloadFiles(Long knowledgeId, HttpServletResponse response, ServletRequest request) throws Exception {

    }

    /*发布知识问题*/
    @RequestMapping(value = "/addKnowledge", method = RequestMethod.POST)
    @ResponseBody
    public Object addKnowledge(@RequestParam(value = "files", required = false) MultipartFile[] files,
            Knowledge knowledge, HttpServletRequest request) {
        Staff staffInfo = SessionInfo.getStaff();
        return knowledgesService.addKnowledge(request, staffInfo, knowledge, files);
    }

    /*更新知识问题*/
    @RequestMapping(value = "/updKnowledge", method = RequestMethod.POST)
    @ResponseBody
    public Object updKnowledge(@RequestParam(value = "files", required = false) MultipartFile[] files,
            Knowledge knowledge, HttpServletRequest request) {
        Staff staffInfo = SessionInfo.getStaff();
        return knowledgesService.updKnowledge(request, staffInfo, knowledge, files);
    }

    /*删除知识*/
    @RequestMapping(value = "/delKnowledgesById", method = RequestMethod.GET)
    @ResponseBody
    public Object delKnowledgesById(Long[] knowledgeIds) {
        Staff staffInfo = SessionInfo.getStaff();
        return knowledgesService.delKnowledgesById(knowledgeIds, staffInfo);
    }

    /*更新知识索引*/
    @RequestMapping(value = "/indexKnowledge")
    @ResponseStatus(HttpStatus.OK)
    public void indexKnowledge(Long knowledgeId) throws ApplicationException {
        Staff staffInfo = SessionInfo.getStaff();
        knowledgesService.indexKnowledge(knowledgeId, staffInfo);
    }

    /*删除知识索引*/
    @RequestMapping(value = "/rmKnowledgeIndex")
    @ResponseStatus(HttpStatus.OK)
    public void rmKnowledgeIndex(Long knowledgeId) throws ApplicationException {
        Staff staffInfo = SessionInfo.getStaff();
        knowledgesService.rmKnowledgeIndex(knowledgeId, staffInfo);
    }

    /*搜索知识*/
    @RequestMapping(value = "/searchKnowledges", method = RequestMethod.POST)
    @ResponseBody
    public Object searchKnowledges(@RequestBody Map<String, Object> paramMap)
            throws ApplicationException, UnsupportedEncodingException {
        return knowledgesService.searchKnowledges(paramMap);
    }

    /*导出EXCEL*/
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(@RequestParam(value = "knowledgeIds") List<Long> knowledgeIds,
            @RequestParam(value = "type") String type, HttpServletResponse response) throws IOException {
        knowledgesService.exportExcel(response, knowledgeIds, type);
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    @ResponseBody
    public Object getMenu() {
        return knowledgesService.getMenu();
    }

    /*获取目录*/
    @RequestMapping(value = "/getMenuPri", method = RequestMethod.GET)
    @ResponseBody
    public Object getMenuPri() {
        Staff staffInfo = SessionInfo.getStaff();
        return knowledgesService.getMenuPri(staffInfo.getId());
    }

    /*获取类型目录*/
    @RequestMapping(value = "/getMenuType", method = RequestMethod.GET)
    @ResponseBody
    public Object getMenuType(@RequestParam(value = "type") String type) {
        return knowledgesService.getMenuType(type);
    }

    /*删除文件夹或者页面*/
    @RequestMapping(value = "/delCatalog", method = RequestMethod.GET)
    @ResponseBody
    public Object delCatalog(Long catalogId) {
        return knowledgesService.delCatalog(catalogId);
    }

    /*修改文件夹页面名称*/
    @RequestMapping(value = "/editCatalog", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void editCatalog(Long catalogId, String newName) {
        knowledgesService.editCatalog(catalogId, newName);
    }

    /*新增文件夹或者目录*/
    @RequestMapping(value = "/addCatalog", method = RequestMethod.POST)
    @ResponseBody
    public Object addCatalog(@RequestBody Map<String, Object> paramMap) {
        return knowledgesService.addCatalog(paramMap);
    }

    /*拖拽文件夹 或者 目录*/
    @RequestMapping(value = "/dragCatalog", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void dragCatalog(@RequestBody Map<String, Object> paramMap) {
        knowledgesService.dragCatalog(paramMap);
    }

    /*判断知识状态*/
    @RequestMapping(value = "/checkState", method = RequestMethod.GET)
    @ResponseBody
    public Object checkState(Long knowledgeId) {
        return knowledgesService.checkState(knowledgeId);
    }
}