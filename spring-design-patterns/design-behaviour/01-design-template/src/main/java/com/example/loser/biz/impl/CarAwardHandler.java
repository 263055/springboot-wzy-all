package com.example.loser.biz.impl;

import com.example.loser.biz.AwardConfig;
import com.example.loser.biz.BaseAwardHandler;
import com.example.loser.core.Component;

@Component
public class CarAwardHandler extends BaseAwardHandler {

    @Override
    protected void doAwardCore(Long userId, AwardConfig config) {
        System.out.println("send car userId:" + userId + " " + config);
    }

    @Override
    protected int getAwardType() {
        return 2;
    }
}
