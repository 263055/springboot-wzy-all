package com.demo.chain.controller;


import com.demo.chain.entity.Clue;
import com.demo.chain.interceptor.RepeatClueContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RepeatClueController {

    @Resource
    private RepeatClueContext repeatClueContext;

    @RequestMapping("/repeat")
    public void lockSave(){
        Clue clue = Clue.builder().phone("134888888").email("123@xx.com").build();
        repeatClueContext.doInterceptor(clue);
    }
}
