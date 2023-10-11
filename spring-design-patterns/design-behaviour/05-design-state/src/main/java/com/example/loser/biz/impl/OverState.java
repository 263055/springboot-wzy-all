package com.example.loser.biz.impl;

import com.example.loser.biz.Task;
import com.example.loser.biz.TaskState;

public class OverState implements TaskState {

    @Override
    public void create(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

    @Override
    public void start(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

    @Override
    public void finish(Task task) {
        System.err.println("任务已经完成 不能操作" + task);
    }

}
