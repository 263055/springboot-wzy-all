package com.demo.util;

public interface RedisProperties {
    String USER_INFO_PREFIX = "user:info:";
    String USER_VERIFY_PREFIX = "user:info:";
    String SENDING_MSG_PREFIX = "sending:msg:";
    String MSG_LOG_PREFIX = "msg:log:";

    Long REGISTER_EXPIRE_TIME = 30L;
    Long SENDING_MSG_EXPIRE_TIME = 5L;

    Long MSG_LOG_EXPIRE_TIME = 2L;
    String REGISTER_TIME_UNIT = "s";
    String VERIFY_TIME_UNIT = "m";
    String SENDING_MSG_TIME_UNIT = "s";
    String MSG_LOG_TIME_UNIT = "s";
}
