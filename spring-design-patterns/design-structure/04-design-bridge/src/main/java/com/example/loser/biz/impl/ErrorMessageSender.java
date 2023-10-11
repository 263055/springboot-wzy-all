package com.example.loser.biz.impl;

import com.example.loser.biz.AbstractMessage;
import com.example.loser.biz.Imessage;
import com.example.loser.biz.Message;

public class ErrorMessageSender extends AbstractMessage {

    public ErrorMessageSender(Imessage imessage) {
        super(imessage);
    }

    @Override
    public void send(Message message, String target) {
        System.out.println("紧急错误消息");
        super.send(message, target);
    }

}
