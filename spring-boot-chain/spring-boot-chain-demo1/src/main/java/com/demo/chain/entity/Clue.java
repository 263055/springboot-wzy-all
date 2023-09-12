package com.demo.chain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Clue {
    //手机号
    private String phone;
    //微信号
    private String webChat;
    //邮箱
    private String email;
    //来源
    private Integer source;
    //媒介
    private Integer media;
}
