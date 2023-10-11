package com.example.loser.biz;

public interface TaskFilter<T> {

    boolean doFilter(T t);

}
