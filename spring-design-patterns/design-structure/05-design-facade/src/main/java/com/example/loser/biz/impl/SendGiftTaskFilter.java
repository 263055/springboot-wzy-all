package com.example.loser.biz.impl;

import com.example.loser.biz.SendGiftMsg;
import com.example.loser.biz.TaskFilter;
import com.example.loser.core.Component;

import java.util.Objects;

@Component
public class SendGiftTaskFilter implements TaskFilter<SendGiftMsg> {

    @Override
    public boolean doFilter(SendGiftMsg sendGiftMsg) {
        return Objects.isNull(sendGiftMsg) || Objects.isNull(sendGiftMsg.getUserId()) || Objects.isNull(sendGiftMsg.getGiftId());
    }

}
