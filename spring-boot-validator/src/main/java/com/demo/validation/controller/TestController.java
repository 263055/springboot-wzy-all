package com.demo.validation.controller;


import com.demo.validation.common.ValidGroup;
import com.demo.validation.dto.UserDTO;
import com.demo.validation.entity.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @RequestMapping("/save")
    public JSONResult<String> save(@Validated @RequestBody UserDTO userDTO) {
        return new JSONResult<String>().success("success");
    }

    @RequestMapping("/update")
    public JSONResult<String> update(@Validated(ValidGroup.class) @RequestBody UserDTO userDTO) {
        return new JSONResult<String>().success("success");
    }


}
