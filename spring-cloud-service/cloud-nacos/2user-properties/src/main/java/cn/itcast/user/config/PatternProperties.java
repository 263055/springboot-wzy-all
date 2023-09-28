package cn.itcast.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 这是一个配置类, 可以读取配置文件中 <br/>
 * pattern 开头的属性,也就是可以把 dateformat 给读取到这个类中
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat; // 单一的配置类做测试
    private String envSharedValue; // 共享的配置类做测试
    private String name;
}
