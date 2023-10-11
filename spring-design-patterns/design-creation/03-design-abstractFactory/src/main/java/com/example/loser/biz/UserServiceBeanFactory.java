package com.example.loser.biz;

import com.example.loser.core.BeanFactory;
import com.example.loser.core.Server;

public class UserServiceBeanFactory implements BeanFactory {

    @Override
    public Server getBean() {
        return new UserService();
    }

}
