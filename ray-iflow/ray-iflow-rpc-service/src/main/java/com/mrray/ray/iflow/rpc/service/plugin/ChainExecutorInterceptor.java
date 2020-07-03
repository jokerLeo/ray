package com.mrray.ray.iflow.rpc.service.plugin;

import com.alibaba.fastjson.JSONObject;
import com.mrray.ray.iflow.core.interceptor.AssessInterceptor;
import com.mrray.ray.iflow.core.interceptor.Invocation;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;
import com.mrray.ray.iflow.dao.base.model.Operation;
import com.mrray.ray.iflow.rpc.api.base.IOperationService;
import com.mrray.ray.iflow.rpc.api.base.IProjectService;
import com.mrray.ray.iflow.rpc.service.base.DefaultMethodExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 审核操作数据库持久化
 *
 * @author lyc
 * @create 2020-03-10 16:55
 **/
@Component
@com.mrray.ray.iflow.core.annotation.ChainExecutorInterceptor
@Slf4j
public class ChainExecutorInterceptor implements AssessInterceptor {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IOperationService operationService;

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        log.info("审核流程执行器开始执行");
        AssessResult assessResult = (AssessResult) invocation.proceed();
        AssessProject assessProject = DefaultMethodExecutor.getAssessProject();
        Operation operation = new Operation();
        BeanUtils.copyProperties(assessResult, operation);
        BeanUtils.copyProperties(assessProject, operation);
        //统一持久化节点参数
        operation.setArgs(JSONObject.toJSONString(assessResult.getArgs()));
        operation.setMoveMethod(assessResult.getMoveTo());

        //保存项目状态
        if (null != assessResult.getProjectStatus()) {
//            ProjectSaveDto projectSaveDto = new ProjectSaveDto();
//            projectSaveDto.setId(assessProject.getProjectId());
//            projectSaveDto.setStatus(assessResult.getProjectStatus().getCode());
//            projectService.updateProject(projectSaveDto);
        }

//        operation.setUserId(UserUtils.getUserId());
//        operation.setUsername(UserUtils.userInfo().getUsername());

        operationService.save(operation);

        return assessResult;
    }
} 
