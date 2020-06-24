package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.iflow.core.AssessManager;
import com.mrray.ray.iflow.core.ChainExecutor;
import com.mrray.ray.iflow.core.enums.ProjectStatus;
import com.mrray.ray.iflow.core.exception.ChainException;
import com.mrray.ray.iflow.core.model.AssessChainVo;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;
import com.mrray.ray.iflow.dao.base.mapper.ProjectMapper;
import com.mrray.ray.iflow.dao.base.model.Project;
import com.mrray.ray.iflow.rpc.service.factory.AssessChainProviderFactory;
import com.mrray.ray.iflow.rpc.service.factory.ChainProviderAdapterFactory;
import com.mrray.ray.iflow.rpc.service.provider.AbstractAssessChainProvider;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 默认的审核流程管理器
 *
 * @author lyc
 * @create 2019-12-12 17:11
 **/
@Service
public class DefaultAssessManager implements AssessManager {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private AssessSessionFactory assessSessionFactory;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AssessResult execute(AssessProject assessProject) {
        validate(assessProject);

        AbstractAssessChainProvider provider = AssessChainProviderFactory.getInstance().createProvider(assessProject);
        BaseChainExecutor executor = provider.get(assessProject);
        ChainExecutor target = assessSessionFactory.getConfiguration().newChainExecutor(executor);
        AssessResult assessResult = target.execute(assessProject);
        //TODO 测试暂时注释
//        BaseChainExecutor plugin = MyPlugin.realTarget(target);
//        provider.afterAssess(assessResult, plugin);
//        ChainProviderCache.getInstance().put(assessProject.getProjectId(), plugin);

        AssessChainVo assessChainVo = ChainProviderAdapterFactory.getInstance().createAdapter(executor).build();
        assessResult.setChain(assessChainVo.getChain());
        assessResult.setHasStop(assessChainVo.isHasStop());
        assessResult.setCurrentIndex(assessChainVo.getCurrentIndex());

        return assessResult;
    }

    @Override
    public AssessChainVo getChain(AssessProject assessProject) {
        AbstractAssessChainProvider provider = AssessChainProviderFactory.getInstance().createProvider(assessProject);
        BaseChainExecutor executor = provider.get(assessProject);
        //ChainProviderCache.getInstance().putIfAbsent(assessProject.getProjectId(), executor);

        AssessChainVo vo = ChainProviderAdapterFactory.getInstance().createAdapter(executor).build();
        vo.setProjectStatus(getProjectStatus(assessProject.getProjectId()));
//        if (vo.getProjectStatus() == ProjectStatus.COMPLETE) {
//            vo.setHasStop(true);
//        }
        return vo;
    }

    private void validate(AssessProject assessProject) {
        Project project = projectMapper.selectById(assessProject.getProjectId());
        if (null == project) {
            throw new ChainException("未找到对应id的项目");
        }
    }

    private ProjectStatus getProjectStatus(Long projectId) {
        Project project = projectMapper.selectById(projectId);
        if (project != null) {
//            Optional<ProjectStatus> status = Arrays.stream(ProjectStatus.values()).filter(s -> project.getStatus().equals(s.getCode())).findFirst();
//            if (status.isPresent()) {
//                return status.get();
//            }
        }

        return null;
    }
} 
