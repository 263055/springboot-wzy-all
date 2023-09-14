package com.demo.validation.exception;


import com.demo.validation.entity.JSONResult;
import com.demo.validation.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //服务器异常处理
    @ExceptionHandler(value = Exception.class)
    public JSONResult<Object> defaultExceptionHandler(Exception e) {
        log.error("系统错误！原因是：", e);
        return new JSONResult<>().fail(ErrorCode.ERR_SYSTEM.getCode(),
                ErrorCode.ERR_SYSTEM.getMessage());
    }

    //自定义异常处理
    @ExceptionHandler(value = CustomException.class)
    public JSONResult<Object> businessExceptionHandler(CustomException e) {
        log.error("业务异常！原因是：", e);
        return new JSONResult<>().fail(e.getErrorCode(),
                e.getErrorCodeDes());
    }

    //空指针异常处理
    @ExceptionHandler(value = NullPointerException.class)
    public JSONResult<Object> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return new JSONResult<>().fail(ErrorCode.NULL_POINTER_ERROR.getCode(),
                ErrorCode.NULL_POINTER_ERROR.getMessage());
    }
    //....当然这里还可以定义很多异常，如果数字转换异常等等

    //参数校验异常处理
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public JSONResult<Object> handleValidatedException(Exception e) {
        if (e instanceof MethodArgumentNotValidException ex) {
            // BeanValidation exception
            return new JSONResult<>().fail(ErrorCode.PARAM_VALIDATION_ERROR.getCode(),
                    ex.getBindingResult().getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; "))
            );
        }
        return new JSONResult<>().fail(ErrorCode.PARAM_VALIDATION_ERROR.getCode(), ErrorCode.PARAM_VALIDATION_ERROR.getCode());
    }
}
