package com.example.loser.biz.impl;

import com.example.loser.biz.Context;
import com.example.loser.biz.ContextObserver;
import com.example.loser.core.Component;

@Component
public class RankContextObserver implements ContextObserver {

    @Override
    public void doContext(Context context) {
        System.out.println("RankContextObserver context = " + context);
    }

}
