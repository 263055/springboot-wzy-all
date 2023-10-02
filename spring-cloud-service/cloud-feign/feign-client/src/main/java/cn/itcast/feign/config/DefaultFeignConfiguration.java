package cn.itcast.feign.config;

import cn.itcast.feign.clients.fallback.UserClientFallbackFactory;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {
    /**
     * NONE：不记录任何日志信息，这是默认值。
     * BASIC：仅记录请求的方法，URL以及响应状态码和执行时间
     * HEADERS：在BASIC的基础上，额外记录了请求和响应的头信息
     * FULL：记录所有请求和响应的明细，包括头信息、请求体、元数据
     */
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }

    /**
     * 步骤二：在feign-api项目中的DefaultFeignConfiguration类中将UserClientFallbackFactory注册为一个Bean：
     */
    @Bean
    public UserClientFallbackFactory userClientFallbackFactory() {
        return new UserClientFallbackFactory();
    }
}
