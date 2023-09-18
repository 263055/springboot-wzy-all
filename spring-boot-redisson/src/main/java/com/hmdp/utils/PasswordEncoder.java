package com.hmdp.utils;


import cn.hutool.core.util.RandomUtil;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 密码加密的通用方法
 * 依赖于 hutool 库
 */
public class PasswordEncoder {
    // 加密密码的方法
    public static String encode(String password) {
        // 生成盐
        String salt = RandomUtil.randomString(20);
        // 加密
        return encode(password,salt);
    }

    // 加密方法
    private static String encode(String password, String salt) {
        // 这里通过字符串拼接
        // 将 salt + @ + '加密后的密码' 进行拼接, 并返回
        // 其中 加密后的密码是通过 生成指定输入的MD5哈希值
        // 注意: MD5 哈希算法不可逆
        return salt + "@" + DigestUtils.md5DigestAsHex((password + salt).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 密码匹配
     * @param encodedPassword 加密后的密码
     * @param rawPassword 原始密码
     * @return 两个密码是否匹配
     */
    public static Boolean matches(String encodedPassword, String rawPassword) {
        if (encodedPassword == null || rawPassword == null) {
            return false;
        }
        if(!encodedPassword.contains("@")){
            throw new RuntimeException("密码格式不正确！");
        }
        String[] arr = encodedPassword.split("@");
        // 获取盐
        String salt = arr[0];
        // 比较
        return encodedPassword.equals(encode(rawPassword, salt));
    }
}
