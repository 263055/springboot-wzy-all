package com.hmdp.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.utils.RedisData;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 对 redis 的一些操作进行了简单的封装
 */
@Component
@Slf4j
public class CacheClient2 {
    private final ExecutorService CACHE_EXECUTOR = Executors.newFixedThreadPool(10);

    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient2(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 将任意Java对象序列化为json并存储在string类型的key中，并且可以设置TTL过期时间
     */
    public void setRedis(Object object, String key, long ttl, TimeUnit timeUnit) {
        String jsonStr = JSONUtil.toJsonStr(object);
        stringRedisTemplate.opsForValue().set(key, jsonStr, ttl, timeUnit);
    }

    /**
     * 任意Java对象序列化为Json并存储在string类型的key中
     * 并且可以设置逻辑过期时间，用于处理缓存击穿问题<br/>
     * 重点: 设置逻辑过期
     */
    public void setLogicRedis(Object object, String key, long ttl, TimeUnit timeUnit) {
        // 设置逻辑过期时间
        RedisData redisData = new RedisData();
        redisData.setData(object);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(ttl)));
        // 存入redis
        String jsonStr = JSONUtil.toJsonStr(redisData);
        stringRedisTemplate.opsForValue().set(key, jsonStr, ttl, timeUnit);
    }

    /**
     * 缓存穿透!!! <br/>
     * 根据指定的key查询缓存，并反序列化为指定类型 <br/>
     * 利用缓存空值的方式解决缓存穿透问题 <br/>
     */
    public <R, ID> R getRedisPenetrate(String keyPrefix, ID id,
                                       Class<R> rClass, Function<ID, R> dbForBack,
                                       long ttl, TimeUnit timeUnit) {
        String key = keyPrefix + id;
        String redisResult = stringRedisTemplate.opsForValue().get(key);

        if (StringUtil.isNotBlank(redisResult)) {
            return JSONUtil.toBean(redisResult, rClass);
        }

        if ("".equals(redisResult)) {
            return null;
        }

        R r = dbForBack.apply(id);
        if (r == null) {
            this.setRedis("", key, ttl, timeUnit);
            return null;
        }
        this.setRedis(r, key, ttl, timeUnit);
        return r;
    }

    /**
     * 缓存击穿 <br/>
     * 根据指定的key查询缓存，并反序列化为指定类型， <br/>
     * 需要利用逻辑过期解决缓存击穿问题 <br/>
     * 加锁限制  <br/>
     */
    public <R, ID> R getRedisBreakdown(String keyPrefix, ID id,
                                       Class<R> rClass, Function<ID, R> dbForBack,
                                       long ttl, TimeUnit timeUnit) {
        String key = keyPrefix + id;
        String lockKey = keyPrefix + "shop:lock";

        String redisResult = stringRedisTemplate.opsForValue().get(key);
        // 缓存数据为空
        if (StrUtil.isBlank(redisResult)) {
            return null;
        }
        // 缓存不为空 将数据转换RedisData
        RedisData redisData = JSONUtil.toBean(redisResult, RedisData.class);
        R bean = JSONUtil.toBean((JSONObject) redisData.getData(), rClass);
        // 未过期 直接返回数据
        if (redisData.getExpireTime() != null && redisData.getExpireTime().isAfter(LocalDateTime.now())) {
            return bean;
        }
        // 缓存过期执行缓存重建
        boolean tryLock = tryLock(lockKey, ttl, timeUnit);
        if (tryLock) {
            CACHE_EXECUTOR.submit(() -> {
                try {
                    R newR = dbForBack.apply(id);
                    this.setLogicRedis(newR, key, ttl, timeUnit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    unLock(lockKey);
                }
            });
        }
        return bean;
    }

    private boolean tryLock(String lockKey, long ttl, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "lock", ttl, timeUnit));
    }

    private void unLock(String lockKey) {
        stringRedisTemplate.delete(lockKey);
    }

}
