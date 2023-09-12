package com.demo.chain.service.impl;

import com.demo.chain.entity.Clue;
import com.demo.chain.service.RepeatClueHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class EmailRepeatHandler implements RepeatClueHandler {
    @Override
    public Clue handler(Clue clue) {
        System.out.println("Email去重逻辑");
        return clue;
    }
}
