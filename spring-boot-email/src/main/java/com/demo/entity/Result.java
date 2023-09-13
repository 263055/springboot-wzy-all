package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    private Integer success;
    private String msg;
    private Object data;
    private List<?> list;
    private int size;

    public static Result success(String msg){
        return new Result(200, msg, null, null, 0);
    }

    public static Result success(Object data, String msg){
        return new Result(200, msg, data, null, 0);
    }

    public static Result success(List<?> list, String msg){
        return new Result(200, msg, null, list, list.size());
    }

    public static Result fail(String msg){
        return new Result(408, msg, null, null, 0);
    }

    public static Result fail(String msg, int code){
        return new Result(code, msg, null, null, 0);
    }
}
