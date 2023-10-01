package cn.itcast.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("cn.itcast.user.mapper")
@SpringBootApplication
public class SentinelUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelUserApplication.class, args);
    }
}
