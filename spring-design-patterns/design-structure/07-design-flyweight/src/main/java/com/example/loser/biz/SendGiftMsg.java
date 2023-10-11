package com.example.loser.biz;

import lombok.Data;

@Data
public class SendGiftMsg {

    private Long userId;

    private Long anchorId;

    private Long giftId;

    private Long sendGiftTime;

}
