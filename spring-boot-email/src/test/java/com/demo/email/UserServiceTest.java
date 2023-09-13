package com.demo.email;


import com.demo.service.UserService;
import com.demo.util.EmailUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.thymeleaf.context.Context;

/**
 * @author wzy
 */
@Slf4j
public class UserServiceTest extends SpringBootDemoEmilSentApplicationTests {

    @Resource
    private EmailUtil emailUtil;

    @Test
    public void sentEmil1() {
        Context context = new Context();
        // 向  thymeleaf 模板中传入参数
        context.setVariable("email", "");
        context.setVariable("url", "generateEmail(token, host, registerPath)");
        // 这里填写你的邮箱
        emailUtil.send("2630559606@qq.com", "你好!!!", context, "registerTemplate");
    }
}
