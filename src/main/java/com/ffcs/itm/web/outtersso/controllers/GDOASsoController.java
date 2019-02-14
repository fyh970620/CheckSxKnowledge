package com.ffcs.itm.web.outtersso.controllers;

import com.bsnnms.exception.ApplicationException;
import com.ffcs.itm.web.outtersso.service.SingleLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/common") // todo: 拆模块迁移中，从 Commontroller 拆分，暂不改入口
/**
 * 广东单点需求
 */
public class GDOASsoController {
    @Autowired
    private SingleLoginService singleLoginService;

    @ResponseBody
    @RequestMapping(value = "/SingleSignOn", method = RequestMethod.GET)
    public void singleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=GBK");
        try {
            singleLoginService.single(request, response);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
