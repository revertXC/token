package com.deer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/skip")
public class SkipController {

    @RequestMapping("login")
    public String goLogin(){
        return "login";
    }



}
