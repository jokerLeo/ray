package com.mrray.ray.iflow.core.interceptor;

import com.mrray.ray.iflow.core.annotation.Interceptor;
import com.mrray.ray.iflow.core.annotation.MethodSignature;
import com.mrray.ray.iflow.core.exception.ChainException;
import com.mrray.ray.iflow.core.exception.ExceptionUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Clinton Begin
 **/
public class MyPlugin implements InvocationHandler {

    private final Object target;
    private final AssessInterceptor interceptor;
    private final Map<Class<?>, Set<Method>> signatureMap;

    private MyPlugin(Object target, AssessInterceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    public static Object wrap(Object target, AssessInterceptor interceptor) {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        if (interfaces.length > 0) {
            return Proxy.newProxyInstance(
                    type.getClassLoader(),
                    interfaces,
                    new MyPlugin(target, interceptor, signatureMap));
        }
        return target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Set<Method> methods = signatureMap.get(method.getDeclaringClass());
            if (methods != null && methods.contains(method)) {
                return interceptor.intercept(new Invocation(target, method, args));
            }
            return method.invoke(target, args);
        } catch (Exception e) {
            throw ExceptionUtil.unwrapThrowable(e);
        }
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(AssessInterceptor interceptor) {
        Interceptor interceptorAnnotation = interceptor.getClass().getAnnotation(Interceptor.class);
        if (interceptorAnnotation == null) {
            throw new ChainException("拦截器" + interceptor.getClass().getName() + "需要@Intercepts注解");
        }
        MethodSignature[] sigs = interceptorAnnotation.value();
        Map<Class<?>, Set<Method>> signatureMap = new HashMap<>(16);
        for (MethodSignature sig : sigs) {
            Set<Method> methods = signatureMap.get(sig.type());
            if (methods == null) {
                methods = new HashSet<>();
                signatureMap.put(sig.type(), methods);
            }
            try {
                Method method = sig.type().getMethod(sig.method(), sig.args());
                methods.add(method);
            } catch (NoSuchMethodException e) {
                throw new ChainException("在 " + sig.type() + " 中找不到方法 " + sig.method() + ". 原因: " + e, e);
            }
        }
        return signatureMap;
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        Set<Class<?>> interfaces = new HashSet<>();
        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                }
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }
}
