package com.deer.controller;

import com.deer.model.UserEntity;
import com.deer.token.JWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("v1/login")
@RestController
public class LoginController {
    public static Map<String, Object> tokenMap = new LinkedHashMap<String, Object>();

    @RequestMapping(method = RequestMethod.POST)
    public String login(UserEntity userEntity){
        String token = null;
        if(userEntity != null && "admin".equals(userEntity.getAccount())){
            //登录成功
            userEntity.setId(1L);
            userEntity.setName("Admin管理员");
            token = JWTUtil.createTokenHMAC256(userEntity);
            tokenMap.put(token,userEntity);
        }else{
            return "faild";
        }
        System.out.println();
        return token;
    }

}
