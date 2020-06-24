package com.mrray.ray.iflow.core.annotation;

import java.lang.annotation.*;

/**
 * 自定义拦截器, 当前支持拦截审核流程执行器ChainExecutor, 方法执行器MethodExecutor, 审核方法AssessMethod
 *
 * @author lyc
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Interceptor {
    MethodSignature[] value();
}

