package com.demo.validation.aop;


import com.demo.validation.annotation.NoJsonResult;
import com.demo.validation.entity.JSONResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.demo.response.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //过滤掉已经加上jsonResult返回值的方法
        return !(returnType.getParameterType().equals(JSONResult.class)
                || returnType.hasMethodAnnotation(NoJsonResult.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //String类型需要特殊处理
        if (returnType.getParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new JSONResult<>().success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return new JSONResult<>().success(body);
    }
}
