package com.demo.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    protected String secret;
    protected String loginExpireTime;
    protected String registerExpireTime;
    protected String loginTimeUnit;
    protected String registerTimeUnit;
}
