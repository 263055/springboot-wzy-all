# spring 异常类处理

## 概述

通过springboot对异常的管理，可以自定义异常，自定义返回的值和状态码

## 流程

定义两个异常类，第一个是BaseException，用来接管所有的异常类
```java
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
```
第二个是自定义异常类，比如JsonException，然后在其他代码地方抛出这个异常即可
```java
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
```
最后一步,统一异常类的拦截,当有代码抛出JsonException异常，就会进入下面的方法
```
    @ExceptionHandler(value = JsonException.class)
    @ResponseBody
    public ApiResponse jsonErrorHandler(JsonException exception) {
        log.error("【JsonException】:{}", exception.getMessage());
        return ApiResponse.ofException(exception);
    }
```