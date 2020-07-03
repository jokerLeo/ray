package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.iflow.core.control.ControlUtils;
import com.mrray.ray.iflow.core.method.AssessMethod;
import com.mrray.ray.iflow.core.method.MethodExecutor;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.NodeResult;
import com.mrray.ray.iflow.rpc.service.Configuration;

/**
 * 默认方法执行器
 *
 * @author lyc
 * @create 2020-03-11 10:00
 **/
public class DefaultMethodExecutor implements MethodExecutor {
    private Configuration configuration;

    private static final ThreadLocal<AssessProject> LOCAL_ASSESS_PROJECT = new ThreadLocal<>();

    private static void setAssessProject(AssessProject assessProject) {
        LOCAL_ASSESS_PROJECT.set(assessProject);
    }

    public static AssessProject getAssessProject() {
        return LOCAL_ASSESS_PROJECT.get();
    }

    protected static void clearAssessProject() {
        LOCAL_ASSESS_PROJECT.remove();
    }

    public DefaultMethodExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public final NodeResult assess(AssessProject assessProject, String methodKey) {
        setAssessProject(assessProject);
        AssessMethod method = configuration.newMethod(methodKey);
        method.init();
        if (pass()) {
            return method.doConfirm();
        } else if (stop()) {
            return method.doRefuse();
        }

        return method.doDefault();
    }

    private boolean pass() {
        return ControlUtils.confirm(getAssessProject());
    }

    private boolean stop() {
        return ControlUtils.stop(getAssessProject());
    }
}
