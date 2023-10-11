package com.example.loser.biz;

import com.example.loser.core.Component;

@Component
public class CommandInvoker {

    public void execute(Command command) {
        System.out.println(command.getClass());
        System.out.println(command);
    }

}
