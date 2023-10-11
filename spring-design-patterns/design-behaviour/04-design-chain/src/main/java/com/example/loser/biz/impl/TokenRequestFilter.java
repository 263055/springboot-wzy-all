package com.example.loser.biz.impl;

import com.example.loser.biz.Request;
import com.example.loser.biz.RequestFilter;
import com.example.loser.biz.RequestFilterChain;
import com.example.loser.biz.Response;
import com.example.loser.core.Component;

import java.util.Objects;

@Component
public class TokenRequestFilter implements RequestFilter {

    @Override
    public void doFilter(Request request, Response response, RequestFilterChain filterChain) {

        System.out.println("TokenRequestFilter index " + sort() + " " + request);
        if (Objects.nonNull(request.getHeaders().get("token"))) {
            filterChain.doFilter(request, response);
        } else {
            throw new RuntimeException("token 为空");
        }

    }

    @Override
    public int sort() {
        return 3;
    }
}
