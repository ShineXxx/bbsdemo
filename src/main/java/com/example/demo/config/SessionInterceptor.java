package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SessionInterceptor implements HandlerInterceptor{
    private static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("---------------------开始进入请求地址拦截----------------------------");
        HttpSession session = request.getSession();
        if(!StringUtils.isEmpty(session.getAttribute("userinfo"))){
            return true;
        }
        else{
            PrintWriter printWriter = response.getWriter();
            printWriter.write("access denied!");
            return false;
        }

    }
}
