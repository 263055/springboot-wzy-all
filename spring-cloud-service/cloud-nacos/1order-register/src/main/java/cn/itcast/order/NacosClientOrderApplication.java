package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class NacosClientOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientOrderApplication.class, args);
    }
}