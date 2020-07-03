package com.mrray.ray.iflow.core;

import com.mrray.ray.iflow.core.model.AssessProject;

/**
 * 评估链提供者接口,提供未经初始化的评估链
 *
 * @author lyc
 **/
public interface AssessChainProvider {
    /**
     * 获取默认的评估链对象
     *
     * @param assessProject
     * @return
     */
    ChainExecutor getDefault(AssessProject assessProject);
} 
