package com.deer.config;

import com.auth0.jwt.interfaces.Claim;
import com.deer.controller.LoginController;
import com.deer.controller.TestController;
import com.deer.model.UserEntity;
import com.deer.token.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader("jwtToken");
        if(token != null){
            System.out.println(token);
           UserEntity userEntity = (UserEntity) LoginController.tokenMap.get(token);
           Map<String, Claim> map = JWTUtil.verifyToken(token,userEntity.getAccount());
           return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
