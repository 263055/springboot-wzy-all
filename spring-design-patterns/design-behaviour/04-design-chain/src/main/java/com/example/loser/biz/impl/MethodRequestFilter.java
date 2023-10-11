package com.example.loser.biz.impl;

import com.example.loser.biz.Request;
import com.example.loser.biz.RequestFilter;
import com.example.loser.biz.RequestFilterChain;
import com.example.loser.biz.Response;
import com.example.loser.core.Component;

@Component
public class MethodRequestFilter implements RequestFilter {

    @Override
    public void doFilter(Request request, Response response, RequestFilterChain filterChain) {

        System.out.println("MethodRequestFilter index " + sort() + " " + request);
        if ("POST".equals(request.getRequestMethod())) {
            filterChain.doFilter(request, response);
        } else {
            throw new RuntimeException("不支持方法类型");
        }

    }

}
