package com.example.loser.biz.impl;

import com.example.loser.biz.Command;
import com.example.loser.biz.CommandInvoker;
import com.example.loser.core.Autowired;
import com.example.loser.core.Component;
import lombok.Data;

@Data
@Component
public class BizCommand implements Command {

    private final String desc = "业务命令";

    @Autowired
    private CommandInvoker commandInvoker;

    @Override
    public void execute() {
        commandInvoker.execute(this);
    }

}
