package com.mrray.ray.iflow.rpc.service;

import com.mrray.ray.common.SpringContextUtil;
import com.mrray.ray.iflow.core.ChainExecutor;
import com.mrray.ray.iflow.core.interceptor.AssessInterceptor;
import com.mrray.ray.iflow.core.interceptor.AssessInterceptorChain;
import com.mrray.ray.iflow.core.method.AssessMethod;
import com.mrray.ray.iflow.core.method.MethodExecutor;
import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.rpc.service.base.AbstractAssessMethod;
import com.mrray.ray.iflow.rpc.service.base.DefaultMethodExecutor;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import lombok.Data;

import java.util.List;

/**
 * 配置
 *
 * @author lyc
 * @create 2020-03-10 15:22
 **/
@Data
public class Configuration {
    private AssessInterceptorChain assessInterceptorChain;
    private AbstractAssessMethod[] assessMethods;
    private List<AssessNode> assessNodeList;

    Configuration() {
        assessInterceptorChain = new AssessInterceptorChain();
    }

    void addInterceptor(AssessInterceptor interceptor) {
        assessInterceptorChain.addInterceptor(interceptor);
    }

    public String getNodeNameByMethodKey(String methodKey) {
        for (AssessNode node : assessNodeList) {
            if (node.getMethodKey().equals(methodKey)) {
                return node.getDescription();
            }
        }

        return null;
    }

    public AssessMethod newMethod(String methodKey) {
        AssessSessionFactory sessionFactory = SpringContextUtil.getBean(AssessSessionFactory.class);
        AssessMethod currentMethod = sessionFactory.getMethod(methodKey);
        return (AssessMethod) assessInterceptorChain.pluginAll(currentMethod);
    }

    public MethodExecutor newMethodExecutor() {
        MethodExecutor methodExecutor = new DefaultMethodExecutor(this);
        return (MethodExecutor) assessInterceptorChain.pluginAll(methodExecutor);
    }

    public ChainExecutor newChainExecutor(ChainExecutor chainExecutor) {
        return (ChainExecutor) assessInterceptorChain.pluginAll(chainExecutor);
    }
} 
