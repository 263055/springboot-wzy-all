package com.demo.chain.interceptor;

import com.demo.chain.entity.Clue;
import com.demo.chain.service.RepeatClueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepeatClueContext {

    //sprig会根据Order注解顺序注入该接口的实现类
    @Autowired
    private List<RepeatClueHandler> list;

    public Clue doInterceptor(Clue clue) {
        for (RepeatClueHandler rch : list) {
            clue = rch.handler(clue);
            if (clue == null) {
                return null;
            }
        }
        return clue;
    }

}
