package com.demo.event.listener;

import cn.hutool.json.JSONUtil;
import com.demo.event.dto.UserDTO;
import com.demo.event.event.UserDTOEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听者
 *  监听用户的事件
 */
@Slf4j
@Configuration
public class RegisterListener {


    @Async
    @EventListener
    public void account(UserDTOEvent<UserDTO> event){
        log.info("账户中心监听到用户注册,{}", JSONUtil.toJsonStr(event.getData()));
    }

    @Async
    @EventListener
    public void marketing(UserDTOEvent<UserDTO> event){
        log.info("营销中心监听到用户注册,{}", JSONUtil.toJsonStr(event.getData()));
    }
}
