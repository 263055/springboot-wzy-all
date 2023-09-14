package com.demo.validation.error;

public enum ErrorCode {

    ERR_SYSTEM("100001", "系统错误"),
    CUSTOM_ERR("100002", "自定义异常"),
    NULL_POINTER_ERROR("100003", "空指针异常"),

    PARAM_VALIDATION_ERROR("100004", "参数校验失败");


    /**
     * 错误code
     */
    private String code;
    /**
     * 错误描述
     */
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
