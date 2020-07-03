package com.mrray.ray.iflow.core.method;

import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.NodeResult;

/**
 * 评估方法执行器接口
 *
 * @author lyc
 * @create 2019-12-13 16:16
 **/
public interface MethodExecutor {
    /**
     * 执行评估
     *
     * @param assessProject
     * @param methodKey
     * @return
     */
    NodeResult assess(AssessProject assessProject, String methodKey);
} 
