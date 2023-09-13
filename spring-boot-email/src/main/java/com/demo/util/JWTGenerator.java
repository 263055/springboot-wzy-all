package com.demo.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JWTGenerator {
    private JwtProperties properties;
    private byte[] secret;

    @Autowired
    public void setProperties(JwtProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void setSecret(){
        secret = properties.secret.getBytes();
    }


    // 生成基础token，封装过程方法，私有
    private String generateBasicToken(Map<String, Object> payloads, Date date, int offset) {
        JWT jwt = JWT.create()
                .setNotBefore(DateUtil.date()) // JWT在指定时间之前是无效的
                .setExpiresAt(DateUtil.offsetDay(date, offset));
        payloads.forEach(
                jwt::setPayload
        );
        return jwt.sign(JWTSignerUtil.hs256(secret));

    }

    // 注册分发token，使用id作为payload
    public String generateToken(String id) {
        HashMap<String, Object> payloads = new HashMap<>();
        Date date = switchStringToDate(properties.loginTimeUnit);
        payloads.put("verify", id);
        return generateBasicToken(payloads, date, Integer.parseInt(properties.loginExpireTime));
    }

    // 生成token的过期时间
    private Date switchStringToDate(String s){
        return switch(s) {
            case "d" -> DateUtil.offsetDay(DateUtil.date(), 1);
            case "h" -> DateUtil.offsetHour(DateUtil.date(), 1);
            case "m" -> DateUtil.offsetMinute(DateUtil.date(), 1);
            case "s" -> DateUtil.offsetSecond(DateUtil.date(), 1);
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    // 验证token，使用私钥解密payload，验证id和email
    public boolean verifyToken(String token, String id) {
        // 组成原始的payload进行验证
        // 首先验证日期和token有效性，无效抛出异常并返回false
        try{
            JWTValidator.of(token).validateDate().validateAlgorithm(JWTSignerUtil.hs256(secret));
        } catch (ValidateException e) {
            e.printStackTrace();
            return false;
        }
        // 验证token的payload，返回payload的验证结果
        JWT of = JWTUtil.parseToken(token);
        String payload = (String)of.getPayload("verify");
        System.out.println(payload);

        return payload.equals(id);
    }

    // 判断 token 是否过期
    public void verifyTokenIsExpire(String token) {
        // 组成原始的payload进行验证
        // 首先验证日期和token有效性，无效抛出异常并返回false
        try{
            JWTValidator.of(token).validateDate().validateAlgorithm(JWTSignerUtil.hs256(secret));
        } catch (RuntimeException e) {
            throw new RuntimeException("token过期");
        }
    }

    // 从token中获取id
    public String getIdFromToken(String token){
        return (String) JWT.of(token).getPayload("verify");
    }



}

