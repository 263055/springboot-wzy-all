package com.demo.retry.controller;

import com.demo.retry.service.TestRetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@EnableRetry
public class TestRetryController {

    private final TestRetryService testRetryService;

    public TestRetryController(TestRetryService testRetryService) {
        this.testRetryService = testRetryService;
    }


    @RequestMapping("/testRetry/{count}")
    public String testRetry(@PathVariable Integer count){
        try {
            return testRetryService.testRetry(count);
        }catch (Exception e){
            log.error("testRetry 异常 ",e);
            return "error";
        }
    }

}
