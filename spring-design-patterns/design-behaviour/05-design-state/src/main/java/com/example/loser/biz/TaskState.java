package com.example.loser.biz;

public interface TaskState {

    void create(Task task);

    void start(Task task);

    void finish(Task task);

}
