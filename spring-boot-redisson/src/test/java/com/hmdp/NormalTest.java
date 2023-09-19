package com.hmdp;

import cn.hutool.core.lang.UUID;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.hmdp.handler.RedisBloomFilter;
import com.hmdp.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.stream.IntStream;

public class NormalTest extends HmDianPingApplicationTests{
    @Resource
    private RedisBloomFilter redisBloomFilter;

    @Test
    void testBitMap() {
        int i = 0b1110111111111111111111111;

        long t1 = System.nanoTime();
        int count = 0;
        while (true) {
            if ((i & 1) == 0) {
                break;
            } else {
                count++;
            }
            i >>>= 1;
        }
        long t2 = System.nanoTime();
        System.out.println("time1 = " + (t2 - t1));
        System.out.println("count = " + count);

        i = 0b1110111111111111111111111;
        long t3 = System.nanoTime();
        int count2 = 0;
        while (true) {
            if(i >>> 1 << 1 == i){
                // 未签到，结束
                break;
            }else{
                // 说明签到了
                count2++;
            }

            i >>>= 1;
        }
        long t4 = System.nanoTime();
        System.out.println("time2 = " + (t4 - t3));
        System.out.println("count2 = " + count2);
    }

    // 密码测试
    @Test
    void testPassword() {
        // 通过自定义的PasswordEncoder方法，将 密码 进行加密
        String password = "hello wzy";
        String enCodePassword = PasswordEncoder.encode(password);
        System.out.println(enCodePassword);
        // 此时的输出值eg:
        // 5vxxrt67ngy69r41ksdg@839d1e8358ea5167fe2a754baf80437e
        // 这是一个  salt + @ + '加密后的密码' 组成的字符串

        // 接下来, 进行密码匹配, 只要地球不爆炸, 这里输出的都应该是true
        Boolean matches = PasswordEncoder.matches(enCodePassword, password);
        System.out.println(matches);
    }

    // 布隆过滤测试
    @Test
    void testBloomFilter1() {
        /*
           expectedInsertions 预期插入值
           这个值的设置相当重要，如果设置的过小很容易导致饱和而导致误报率急剧上升，如果设置的过大，也会对内存造成浪费，所以要根据实际情况来定
           fpp                误差率，例如：0.01,表示误差率为1%
         */
        int expectedInsertions = 100_0000;//期望插入的数据量
        double fpp = 0.01; //误判率

        // 创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, fpp);
        // BloomFilter<Long> filter2 = BloomFilter.create(Funnels.longFunnel(), expectedInsertions, fpp);
        // BloomFilter<String> filter3 = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), expectedInsertions, fpp);
        System.out.println("------------未插入数据前检测-----------");
        for (int i = 899990; i < 90_0003; i++) {
            // 判断指定元素是否存在
            System.out.println(i + " 是否存在: " + filter.mightContain(i));
        }

        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 90_0000; i++) {//向过滤器添加90万数据
            // 将元素添加进布隆过滤器
            filter.put(i);
        }
        System.out.println("添加90_0000条数据耗时:" + (System.currentTimeMillis() - s1) + " ms");

        System.out.println("------------插入数据后检测-----------");
        long s2 = System.currentTimeMillis();
        for (int i = 899990; i < 90_0003; i++) {
            // 判断指定元素是否存在
            System.out.println(i + " 是否存在: " + filter.mightContain(i));
        }
        System.out.println("检测耗时:" + (System.currentTimeMillis() - s2) + " ms");
    }

    // 布隆过滤测试, 加入测试数据
    @Test
    void testBloomFilterAddTest() {
        long l1 = System.currentTimeMillis();
        IntStream.range(1, 10001).forEach(i -> redisBloomFilter.put("bit.a", UUID.randomUUID().toString()));
        long l2 = System.currentTimeMillis();
        System.out.println("向 redis 中插入 布隆过滤的10000条数据费时: " + (l2 - l1));
    }

    // 布隆过滤测试, 对加入的测试数据进行测试
    @Test
    void testBloomFilter() {
        int errors = 0 ;
        for (int i = 0; i < 100; i++) {
            if (redisBloomFilter.mightContain("bit.a", UUID.randomUUID().toString())) {
                errors += 1 ;
            }
        }
        System.out.println(errors / 10000d);
    }
}