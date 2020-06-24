package com.mrray.ray.iflow.rpc.service.factory;

import com.mrray.ray.common.SpringContextUtil;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.rpc.service.provider.AbstractAssessChainProvider;
import com.mrray.ray.iflow.rpc.service.provider.DefaultChainProvider;

/**
 * 评估处理类工厂
 *
 * @author lyc
 * @create 2019-12-12 15:14
 **/
public class AssessChainProviderFactory {

    private AssessChainProviderFactory() {
    }

    public static AssessChainProviderFactory getInstance() {
        return HelperHolder.INSTANCE;
    }

    private static class HelperHolder {
        private static final AssessChainProviderFactory INSTANCE = new AssessChainProviderFactory();
    }

    public AbstractAssessChainProvider createProvider(AssessProject assessProject) {
        //TODO 根据实际情况产生评估链生成者
        return SpringContextUtil.getBean(DefaultChainProvider.class);
    }
} 
