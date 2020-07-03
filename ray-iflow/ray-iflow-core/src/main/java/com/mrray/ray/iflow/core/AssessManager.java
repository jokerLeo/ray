package com.mrray.ray.iflow.core;

import com.mrray.ray.iflow.core.model.AssessChainVo;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;

/**
 * 评估管理器
 *
 * @author lyc
 * @create 2020-03-10 17:23
 **/
public interface AssessManager {
    /**
     * 执行审核操作
     *
     * @param assessProject
     * @return 执行审核操作后项目的当前审核流程视图
     */
    AssessResult execute(AssessProject assessProject);

    /**
     * 获取项目的审核流程视图
     *
     * @param assessProject
     * @return
     */
    AssessChainVo getChain(AssessProject assessProject);
} 
