package com.ffcs.itm.web.knowledges.service;

import jxl.JXLException;
import jxl.Workbook;
import jxl.write.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeExcelExporter {

    private HttpServletResponse response;
    private String fileName;
    private List<String[]> titleList;
    List<List<List<String>>> contentList;
    private WritableWorkbook workbook;

    /*单个sheet页*/
    public void export(HttpServletResponse response, String fileName, String[] titles, List<List<String>> content)
            throws IOException {
        this.workbook = Workbook.createWorkbook(response.getOutputStream());
        this.response = response;
        this.fileName = fileName;
        List<String[]> titleList = new ArrayList<>();
        titleList.add(titles);
        this.titleList = titleList;
        List<List<List<String>>> contentList = new ArrayList<List<List<String>>>();
        contentList.add(content);
        this.contentList = contentList;

        start();
    }

    /*多个sheet页*/
    public void export(HttpServletResponse response, String fileName, List<String[]> titleList,
            List<List<List<String>>> contentList) throws IOException {

        this.workbook = Workbook.createWorkbook(response.getOutputStream());
        this.response = response;
        this.fileName = fileName;
        this.titleList = titleList;
        this.contentList = contentList;

        start();
    }

    private void start() throws IOException {
        WritableSheet sheet;
        try {
            setResponse();
            for (int i = 0; i < titleList.size(); i++) {
                sheet = this.workbook.createSheet("Sheet" + (i + 1), 1);
                createHeader(sheet, titleList.get(i));
                createContent(sheet, contentList.get(i));
            }
            this.workbook.write();
            this.workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createHeader(WritableSheet sheet, String[] titles) throws JXLException {
        WritableCellFormat titleFormat = getTitleFormat();

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            sheet.addCell(new Label(i, 0, title, titleFormat));
        }
    }

    private void createContent(WritableSheet sheet, List<List<String>> content) throws Exception {
        int i = 1;
        for (List<String> lineData : content) {
            int j = 0;
            for (String d : lineData) {
                sheet.addCell(new Label(j, i, d));
                j++;
            }
            i++;
        }
    }

    private void setResponse() throws UnsupportedEncodingException {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", getResponseHeader());
    }

    private String getResponseHeader() throws UnsupportedEncodingException {
        return "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xls";//StringUtils.gbToUtf8(fileName);
    }

    private WritableCellFormat getTitleFormat() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
        return new WritableCellFormat(font);
    }


    /*单个sheet页*/
    public void exportOpentsdb(HttpServletResponse response, String fileName, String[] titles,
            List<List<String>> content) throws IOException {
        this.workbook = Workbook.createWorkbook(response.getOutputStream());
        this.response = response;
        this.fileName = fileName;
        List<String[]> titleList = new ArrayList<>();
        titleList.add(titles);
        this.titleList = titleList;
        List<List<List<String>>> contentList = new ArrayList<List<List<String>>>();
        contentList.add(content);
        this.contentList = contentList;

        exportOpentsdbStart();
    }

    private void exportOpentsdbStart() throws IOException {
        WritableSheet sheet;
        try {
            setResponse();
            for (int i = 0; i < titleList.size(); i++) {
                sheet = this.workbook.createSheet("Sheet" + (i + 1), 1);
                sheet.setColumnView(0, 20);
                sheet.setColumnView(1, 50);
                sheet.setColumnView(2, 30);
                sheet.setColumnView(3, 20);
                createHeader(sheet, titleList.get(i));
                createContent(sheet, contentList.get(i));
            }
            this.workbook.write();
            this.workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
