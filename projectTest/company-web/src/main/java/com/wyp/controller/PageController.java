package com.wyp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {

    @RequestMapping("staff")
    public String pageStaff(){
        return "staff";
    }

    @RequestMapping("home")
    public String pageHome(){
        return "home";
    }
}
