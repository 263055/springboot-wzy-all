package com.demo.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件
 * 用户事件
 */
public class UserDTOEvent<T> extends ApplicationEvent {

    private T data;

    public UserDTOEvent(T source) {
        super(source);
        this.data = source;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
