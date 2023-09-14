package com.demo.validation.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 返回json实体对象
 */
@Data
public class JSONResult<T> implements Serializable {
    public static final String SUCCESS = "0";
    public static final String FAIL = "-1";
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 6974189491915284638L;
    private String code;
    private String msg;
    private T data;

    public static boolean isNotNull(JSONResult res) {
        return res != null && SUCCESS.equals(res.getCode()) && res.getData() != null;
    }

    public JSONResult<T> success(T data) {
        this.code = JSONResult.SUCCESS;
        this.data = data;
        return this;
    }

    public JSONResult<T> fail(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public T data(T defaultVal) {
        if (SUCCESS.equals(code) && data != null) {
            return data;
        }
        return defaultVal;
    }

    public T data() {
        return data(null);
    }
}
