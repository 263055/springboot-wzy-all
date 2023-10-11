package com.example.loser.biz.adapter;

public interface TaskContextAdapter<T> {

    TaskContext adapter(int actId, T msg);

}
