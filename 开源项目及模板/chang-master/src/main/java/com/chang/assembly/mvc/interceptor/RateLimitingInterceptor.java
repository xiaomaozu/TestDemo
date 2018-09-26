package com.chang.assembly.mvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RateLimitingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("开始频率限制");
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/sms/reg/**");
        if (antPathRequestMatcher.matches(request)) {
            System.out.println(antPathRequestMatcher);
        }
        return super.preHandle(request, response, handler);
    }
}
