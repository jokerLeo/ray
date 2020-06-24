package com.mrray.ray.iflow.rpc.service.factory;

import com.mrray.ray.iflow.rpc.service.base.AbstractChainProviderAdapter;
import com.mrray.ray.iflow.rpc.service.base.BaseChainExecutor;

/**
 * 评估链适配器工厂，根据当前用户角色生成适配器
 *
 * @author lyc
 * @create 2019-12-27 9:44
 **/
public class ChainProviderAdapterFactory {
    private ChainProviderAdapterFactory() {
    }

    public static ChainProviderAdapterFactory getInstance() {
        return ChainProviderAdapterFactory.HelperHolder.INSTANCE;
    }

    private static class HelperHolder {
        private static final ChainProviderAdapterFactory INSTANCE = new ChainProviderAdapterFactory();
    }

    public AbstractChainProviderAdapter createAdapter(BaseChainExecutor poolObject) {
        //TODO 后期扩展，当有多类型审核流程时，可能需要同时根据角色和流程获取适配器
//        UserInfoVo userInfo = UserUtils.userInfo();
//        if (userInfo.getOrganizationType() == OrganizationType.ENTERPRISE) {
//            return new EnterpriseChainProviderAdapter(poolObject);
//        } else if (userInfo.getOrganizationType() == OrganizationType.BANK) {
//            return new BankChainProviderAdapter(poolObject);
//        }
//
//        return new ManagerChainProviderAdapter(poolObject);
        return null;
    }
} 
