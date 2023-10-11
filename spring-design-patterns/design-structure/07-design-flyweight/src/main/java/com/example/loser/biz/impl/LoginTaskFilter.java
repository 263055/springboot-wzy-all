package com.example.loser.biz.impl;

import com.example.loser.biz.LoginMsg;
import com.example.loser.biz.TaskFilter;
import com.example.loser.core.Component;

import java.util.Objects;

@Component
public class LoginTaskFilter implements TaskFilter<LoginMsg> {

    @Override
    public boolean doFilter(LoginMsg loginMsg) {
        return Objects.isNull(loginMsg) || Objects.isNull(loginMsg.getUserId());
    }
}
