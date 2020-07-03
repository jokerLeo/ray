package com.mrray.ray.iflow.rpc.service.provider;

import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.rpc.service.base.BaseChainExecutor;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认处理链
 *
 * @author lyc
 * @create 2019-12-12 15:41
 **/
@Component
public class DefaultChainProvider extends AbstractAssessChainProvider {
    @Autowired
    private AssessSessionFactory container;

    @Override
    public BaseChainExecutor getDefault(AssessProject assessProject) {
        BaseChainExecutor baseChainExecutor = new BaseChainExecutor();
        baseChainExecutor.addChainNode(container.get("ToCreateMethod"));
        baseChainExecutor.addChainNode(container.get("ToApplyMethod"));

        return baseChainExecutor;
    }
}
