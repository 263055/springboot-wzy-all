package com.demo.util;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.json.JSONUtil;
import com.demo.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.demo.util.RedisProperties.*;

@Component
public class RedisCache {

    @Resource
    private StringRedisTemplate stringTemplate;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private void set(String key, String value, long expireTime) {
        stringTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES);
    }

    private String get(String key) {
        return stringTemplate.opsForValue().get(key);
    }


    public void setRegisterInfo(String id, User user) {
        set(USER_INFO_PREFIX + id, JSONUtil.toJsonStr(user), switchDateTime(REGISTER_EXPIRE_TIME, REGISTER_TIME_UNIT));
    }

    public User getRegisterInfo(String id) {
        String json = get(USER_INFO_PREFIX + id);
        return JSONUtil.toBean(json, User.class);
    }

    public void setVerifyCode(String id, String code) {
        set(USER_VERIFY_PREFIX + id, code, switchDateTime(SENDING_MSG_EXPIRE_TIME, VERIFY_TIME_UNIT));
    }

    public String getVerifyCode(String id) {
        return get(USER_VERIFY_PREFIX + id);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public void deleteObject(final String key) {
        redisTemplate.delete(USER_VERIFY_PREFIX + key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public boolean deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection) > 0;
    }

    private void setHash(String key, Map<String, Object> map, long expireTime) {
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    private void setMap(String id, Map<String, Object> map) {
        setHash(id, map, switchDateTime(SENDING_MSG_EXPIRE_TIME, SENDING_MSG_TIME_UNIT));
    }

    private void setObj(String id, Object obj) {
        Map<String, Object> map = JSONUtil.toBean(JSONUtil.toJsonStr(obj), Map.class);
        setMap(id, map);
    }
    private <T> T getObj(String id, Class<T> clazz){
        Map<Object, Object> map = getHash(id);
        String json  = JSONUtil.toJsonStr(map);
        return JSONUtil.toBean(json,clazz);
    }

    private Map<Object,Object> getHash(String id){
        return redisTemplate.opsForHash().entries(id);
    }

    private long switchDateTime(long time, String timeUnit) {
        return switch (timeUnit) {
            case "s" -> time * 1000;
            case "m" -> time * 1000 * 60;
            case "h" -> time * 1000 * 60 * 60;
            case "d" -> time * 1000 * 60 * 60 * 24;
            default -> throw new ValidateException("时间单位错误，请检查redis部分自定义配置文件");
        };
    }
}
