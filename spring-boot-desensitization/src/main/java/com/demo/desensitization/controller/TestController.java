package com.demo.desensitization.controller;

import com.demo.desensitization.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @RequestMapping("/test")
    public User testDesensitization(){
        User user = User.builder().
                idCard("188888888888888888").
                phone("18888888888").
                bankCard("188888888888").
                address("北京市朝阳区").build();
        System.out.println(user);
        return user;
    }
}
