package com.mrray.ray.iflow.core.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clinton Begin
 **/
public class AssessInterceptorChain {

    private final List<AssessInterceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target) {
        for (AssessInterceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(AssessInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<AssessInterceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }

}
