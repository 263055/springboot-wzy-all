package com.example.loser.biz.impl.msg;

import com.example.loser.biz.Imessage;
import com.example.loser.biz.Message;

public class WxMessage implements Imessage {

    @Override
    public void send(Message message, String target) {
        System.out.println("发送消息到企业微信群");
        System.out.println();
    }

}
