package com.hmdp;

import com.hmdp.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;

public class NormalTest {

    @Test
    void testBitMap() {
        int i = 0b1110111111111111111111111;

        long t1 = System.nanoTime();
        int count = 0;
        while (true){
            if ((i & 1) == 0){
                    break;
            }else{
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
}