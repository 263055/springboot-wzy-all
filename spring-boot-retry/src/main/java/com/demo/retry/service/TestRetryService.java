package com.demo.retry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
public class TestRetryService {

    /**
     * 1.RemoteAccessException 远程访问异常触发重试机制
     * 2.maxAttempts 重试次数 默认为3次
     * 3.delay 延迟1000ms执行
     * 4.每次延迟提高1.5倍
     *
     * 注意点：
     * 1.testRetry方法和recover方法返回值一致
     * 2.testRetry异常类型和recover参数类型一致
     */
    @Retryable(value = RemoteAccessException.class,maxAttempts = 3,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public String testRetry(int count) throws Exception{
        if(count == 10){
            log.info("---------------{}", LocalTime.now());
            throw new RemoteAccessException("远程调用异常");
        }
        return "success";
    }

    /**
     * 用于方法，用于@Retryable失败时的“兜底”处理方法
     */
    @Recover
    public String recover(RemoteAccessException e){
        log.info("远程调用接口失败",e);
        return "recover success";
    }

}
