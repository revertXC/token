package com.deer.controller;


import com.alibaba.fastjson.JSON;
import com.deer.model.UserEntity;
import com.deer.token.JWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String getTest(){
        return "success";
    }



    @RequestMapping("corsReq")
    public String CorsReq(HttpServletRequest request){
        return JSON.toJSONString(getMap(request));
    }

    @GetMapping("greeting")
    public String greeting(HttpServletRequest request) {

        return JSON.toJSONString(getMap(request));
    }

    private Map getMap(HttpServletRequest request){
        Map<String,String> map = new LinkedHashMap();
        map.put("ContextPath",request.getContextPath());
        map.put("PathInfo",request.getPathInfo());
        map.put("RequestURI",request.getRequestURI());
        map.put("ServletPath",request.getServletPath());
        map.put("content","Cors跨域");
        return map;
    }

}
