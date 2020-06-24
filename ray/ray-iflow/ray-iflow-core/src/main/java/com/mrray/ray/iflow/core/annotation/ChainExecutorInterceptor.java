package com.mrray.ray.iflow.core.annotation;

import com.mrray.ray.iflow.core.ChainExecutor;
import com.mrray.ray.iflow.core.model.AssessProject;

import java.lang.annotation.*;

/**
 * 自定义拦截器, 当前支持拦截审核流程执行器ChainExecutor, 方法执行器MethodExecutor, 审核方法AssessMethod
 *
 * @author lyc
 **/
@Interceptor({@MethodSignature(method = "execute", type = ChainExecutor.class, args = {AssessProject.class})})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ChainExecutorInterceptor {
}

