package com.mrray.ray.iflow.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法签名
 *
 * @author lyc
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface MethodSignature {
    Class<?> type();

    String method();

    Class<?>[] args();
}