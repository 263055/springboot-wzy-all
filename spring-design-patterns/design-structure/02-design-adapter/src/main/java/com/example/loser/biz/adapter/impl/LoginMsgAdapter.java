package com.example.loser.biz.adapter.impl;

import com.example.loser.biz.adapter.TaskContext;
import com.example.loser.biz.adapter.TaskContextAdapter;
import com.example.loser.biz.mq.LoginMsg;
import com.example.loser.core.Component;

@Component
public class LoginMsgAdapter implements TaskContextAdapter<LoginMsg> {

    @Override
    public TaskContext adapter(int actId, LoginMsg msg) {
        TaskContext taskContext = new TaskContext();
        taskContext.setActId(actId);
        taskContext.setUserId(msg.getUid());
        taskContext.setTime(msg.getLoginTime().getTime());
        taskContext.setNum(1);
        taskContext.setType(1);
        return taskContext;
    }

}
