package com.demo.event.controller;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.demo.event.dto.UserDTO;
import com.demo.event.event.UserDTOEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class UserController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     *发布者
     * 注册成功后，发布用户事件
     */
    @RequestMapping("/register")
    public void register(@RequestBody UserDTO userDTO){
        log.info("用户注册操作");
        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        long id = snowflake.nextId();
        userDTO.setId(id);
        applicationEventPublisher.publishEvent(new UserDTOEvent<>(userDTO));
    }
}
