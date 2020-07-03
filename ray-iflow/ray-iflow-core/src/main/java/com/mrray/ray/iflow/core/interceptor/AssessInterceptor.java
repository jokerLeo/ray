package com.mrray.ray.iflow.core.interceptor;

/**
 * @author Clinton Begin
 **/
public interface AssessInterceptor {

    Object intercept(Invocation invocation) throws Throwable;

    default Object plugin(Object target) {
        return MyPlugin.wrap(target, this);
    }

}
