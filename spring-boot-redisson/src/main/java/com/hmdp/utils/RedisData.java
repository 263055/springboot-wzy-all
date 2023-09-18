package com.hmdp.utils;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 封装redis缓存逻辑删除对象
 * 主要用于 设置一个逻辑过期, 便于应对缓存穿透等情况
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
