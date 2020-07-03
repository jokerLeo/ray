package com.mrray.ray.iflow.core;

import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;

/**
 * 评估流程执行器
 *
 * @author lyc
 * @create 2020-03-11 10:54
 **/
public interface ChainExecutor {
    /**
     * 执行评估流程
     *
     * @param assessProject
     * @return
     */
    AssessResult execute(AssessProject assessProject);

    /**
     * 检查是否有执行权限
     *
     * @param currentNode
     * @return
     */
    default boolean permissionCheck(AssessNode currentNode) {
        return true;
    }
} 
