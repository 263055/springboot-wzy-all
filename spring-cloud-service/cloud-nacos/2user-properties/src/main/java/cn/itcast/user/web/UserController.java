package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简单的测试一下, 用 nacos 操作对配置类的读取
 */
@Slf4j
@RestController
@RequestMapping("/user")
// 当使用 ConfigurationProperties 注解的时候, 可以自动刷新
// 而 RefreshScope 注解 仅仅会对  Value 注解进行自动刷新
@RefreshScope
public class UserController {

    @Resource
    private UserService userService;
    // /**
    //  * 路径： /user/110
    //  *
    //  * @param id 用户id
    //  * @return 用户
    //  */
    // @GetMapping("/{id}")
    // public User queryById(@PathVariable("id") Long id,
    //                       @RequestHeader(value = "Truth", required = false) String truth) throws InterruptedException {
    //     if (id == 1) {
    //         // 休眠，触发熔断
    //         Thread.sleep(60);
    //     } else if (id == 2) {
    //         throw new RuntimeException("故意出错，触发熔断");
    //     }
    //     return userService.queryById(id);
    // }

    /**
     * 读取配置类的另一种方法:
     * 通过 value 注解可以直接拿到该属性
     */
    @NacosValue("${pattern.dataformat}")
    private String dateformat;

    /**
     * 下面的代码, 是根据 config 配置类获取配置文件中的属性
     */
    @Resource
    private PatternProperties properties;

    @GetMapping("prop")
    public PatternProperties properties() {
        return properties;
    }

    @GetMapping("dateformat")
    public String dateformat() {
        return dateformat;
    }

    @GetMapping("now")
    public String now() {
        // 如果使用 value 注解，这里需要换成 dateformat
        // DateTimeFormatter dateTimeFormatters = DateTimeFormatter.ofPattern(dateformat);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(properties.getDateformat());
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}