package com.ffcs.itm.web.sso.support;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteTool {
    private final static String DEFAULT_PAGE_URL = "/";
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    public RouteTool(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    public void goToDefaultPage() {
        redirectTo(DEFAULT_PAGE_URL);
    }
    
    public void redirectTo(String url) {
        String relativeURL = url;
        
        if (StringUtils.isBlank(relativeURL)) {
            relativeURL = DEFAULT_PAGE_URL;
        }
        
        if (!relativeURL.startsWith("/")) {
            relativeURL += "/";
        }
        
        relativeURL = request.getContextPath() + relativeURL;
        
        try {
            response.sendRedirect(relativeURL);
        } catch (IOException e) {
            throw new RuntimeException("页面跳转失败 url:" + url, e);
        }
    }
}
