package com.ffcs.itm.web.check.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/check/start")
public class StartController {

    @RequestMapping(value ="/index", method = RequestMethod.GET)
    public String toIndex() {
        return "/traineeExamineSys/index";
    }

}
