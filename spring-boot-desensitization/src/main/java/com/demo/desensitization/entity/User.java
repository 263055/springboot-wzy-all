package com.demo.desensitization.entity;

import com.demo.desensitization.annotation.Desensitization;
import com.demo.desensitization.constant.DesensitizationTypeEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    //手机号
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    //身份证号
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;

    //银行卡
    @Desensitization(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;

    @Desensitization(type = DesensitizationTypeEnum.CUSTOM_RULE,start = 0,end = 3)
    private String address;


}
