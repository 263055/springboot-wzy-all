package com.example.loser.biz.impl;

import com.example.loser.biz.AwardConfig;
import com.example.loser.biz.BaseAwardHandler;
import com.example.loser.core.Component;

@Component
public class VipAwardHandler extends BaseAwardHandler {

    @Override
    protected void doAwardCore(Long userId, AwardConfig config) {
        System.out.println("send vip userId:" + userId + " " + config);
    }

    @Override
    protected int getAwardType() {
        return 1;
    }
}
