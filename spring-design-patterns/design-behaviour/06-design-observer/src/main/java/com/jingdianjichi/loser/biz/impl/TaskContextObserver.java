package com.jingdianjichi.loser.biz.impl;

import com.jingdianjichi.loser.biz.Context;
import com.jingdianjichi.loser.biz.ContextObserver;
import com.jingdianjichi.loser.core.Component;

@Component
public class TaskContextObserver implements ContextObserver {

    @Override
    public void doContext(Context context) {
        System.out.println("TaskContextObserver context = " + context);
    }

}
