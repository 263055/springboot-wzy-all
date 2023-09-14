package com.demo.validation.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 不需要包装的返回值
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface NoJsonResult {
}
