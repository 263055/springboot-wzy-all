package com.example.springbootredisson.interceptor;

import com.example.springbootredisson.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * @author CHEN
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {       //获取用户
        if (UserHolder.getUser() == null) {
            //不存在用户 拦截
            response.setStatus(401);
            return false;
        }
        //存在用户放行
        return true;
    }
}
