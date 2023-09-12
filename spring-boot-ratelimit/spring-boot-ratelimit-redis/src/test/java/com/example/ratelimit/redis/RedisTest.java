package com.example.ratelimit.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ratelimit.redis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoRatelimitRedisApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 普通的测试
     * 普通的序列化测试
     */
    @Test
    public void name() {
        redisTemplate.opsForValue().set("wzyz", "a");
        Object wzyz = redisTemplate.opsForValue().get("wzyz");
        System.out.println(wzyz);
    }

    /**
     * 测试反序列化工具，这里使用  StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void name1() throws IOException {
        User user = new User("wzy0", 19);
        // 手动序列化
        String json = mapper.writeValueAsString(user);

        // 写入数据到redis
        stringRedisTemplate.opsForValue().set("wzy:100", json);

        // 读取数据
        String s = stringRedisTemplate.opsForValue().get("wzy:100");
        User user1 = mapper.readValue(s, User.class);
        System.out.println(user1);
    }
}
