package com.mrray.ray.iflow.rpc.service.session;

import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.rpc.service.Configuration;
import com.mrray.ray.iflow.rpc.service.base.AbstractAssessMethod;

/**
 * 评估会话工厂接口
 *
 * @author lyc
 * @create 2020-03-10 15:21
 **/
public interface AssessSessionFactory {

    /**
     * 获取配置
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 获取评估节点
     *
     * @param methodKey
     * @return
     */
    AssessNode get(String methodKey);

    /**
     * 获取评估方法
     *
     * @param methodKey
     * @return
     */
    AbstractAssessMethod getMethod(String methodKey);
} 
