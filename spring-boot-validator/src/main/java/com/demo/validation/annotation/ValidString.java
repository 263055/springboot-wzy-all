package com.demo.validation.annotation;


import com.demo.validation.common.ValidEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(value = ValidString.List.class)
//限定注解的校验类
@Constraint(validatedBy = ValidEnumValidator.class)
public @interface ValidString {

    //校验出错时默认返回的信息
    String message() default "数据不在取值范围内";

    //分组校验
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //限定取值范围
    String[] value();

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @interface List {
        ValidString[] value();
    }
}
