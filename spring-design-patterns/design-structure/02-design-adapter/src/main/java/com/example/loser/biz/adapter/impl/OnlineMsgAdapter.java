package com.example.loser.biz.adapter.impl;

import com.example.loser.biz.adapter.TaskContext;
import com.example.loser.biz.adapter.TaskContextAdapter;
import com.example.loser.biz.mq.OnlineMsg;
import com.example.loser.core.Component;

@Component
public class OnlineMsgAdapter implements TaskContextAdapter<OnlineMsg> {

    @Override
    public TaskContext adapter(int actId, OnlineMsg msg) {
        TaskContext taskContext = new TaskContext();
        taskContext.setActId(actId);
        taskContext.setUserId(msg.getUserId());
        taskContext.setTime(msg.getOnlineTime());
        taskContext.setNum(1);
        taskContext.setType(2);
        return taskContext;
    }

}
