package com.mrray.ray.iflow.rpc.service.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mrray.ray.iflow.core.AssessChainProvider;
import com.mrray.ray.iflow.core.ChainExecutor;
import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;
import com.mrray.ray.iflow.dao.base.model.Chain;
import com.mrray.ray.iflow.rpc.api.base.IChainService;
import com.mrray.ray.iflow.rpc.api.base.IOperationService;
import com.mrray.ray.iflow.rpc.service.base.BaseChainExecutor;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象评估链提供者
 *
 * @author lyc
 * @create 2019-12-16 15:14
 **/
@Component
@Slf4j
public abstract class AbstractAssessChainProvider implements AssessChainProvider {
    @Autowired
    private AssessSessionFactory assessSessionFactory;
    @Autowired
    private IChainService chainService;
    @Autowired
    private IOperationService operationService;

    public BaseChainExecutor get(AssessProject assessProject) {
        //TODO 暂时去除缓存用于测试，后期考虑缓存同步
        //ChainExecutor executor = ChainProviderCache.getInstance().get(assessProject.getProjectId());
//        if (null != executor) {
//            log.info("[flow] get project {} chain from cache", assessProject.getProjectId());
//            return executor;

        ChainExecutor poolObject = getFromDatabase(assessProject.getProjectId());
        if (null == poolObject) {
            poolObject = getDefault(assessProject);
            if (poolObject != null) {
                //TODO
                //List<AssessNode> assessNodes = poolObject.getAssessNodeList();
                List<AssessNode> assessNodes = new ArrayList<>();
                List<String> methodList = new ArrayList<>();
                for (AssessNode assessNode : assessNodes) {
                    methodList.add(assessNode.getMethodKey());
                }

                Chain chain = new Chain();
                chain.setProjectId(assessProject.getProjectId());
                chain.setChainMethodList(JSON.toJSONString(methodList));
                chainService.save(chain);
            }
        }

        //return operationService.initCurrentPoolObject(poolObject, assessProject);
        return null;
    }

    private void appendNodeToExecutor(BaseChainExecutor executor, List<String> methodList) {
        if (null == executor || CollectionUtils.isEmpty(methodList)) {
            return;
        }

        methodList.forEach(s -> {
            AssessNode node = assessSessionFactory.get(s);
            if (null != node) {
                executor.addChainNode(node);
            }
        });
    }

    private void appendNodeToProvider(Long projectId, List<String> methodList) {
        if (null == projectId || CollectionUtils.isEmpty(methodList)) {
            return;
        }

        //TODO
        //Chain chain = chainService.getByProjectId(projectId);
        Chain chain = chainService.getById(projectId);
        if (chain != null) {
            List<String> list = (List<String>) JSONObject.parse(chain.getChainMethodList());
            list.addAll(methodList);
            chain.setChainMethodList(JSONObject.toJSONString(list));
        }

        chainService.updateById(chain);
    }

    private ChainExecutor getFromDatabase(Long projectId) {
        //TODO
        //Chain chain = chainService.getByProjectId(projectId);
        Chain chain = chainService.getById(projectId);
        if (chain == null) {
            return null;
        }

        BaseChainExecutor baseChainExecutor = new BaseChainExecutor();
        List<AssessNode> assessNodeList = new ArrayList<>();
        List<String> methodList = (List<String>) JSONObject.parse(chain.getChainMethodList());
        for (String method : methodList) {
            AssessNode node = assessSessionFactory.get(method);
            if (node != null) {
                assessNodeList.add(node);
            }
        }
        baseChainExecutor.setAssessNodeList(assessNodeList);
        return baseChainExecutor;
    }

    public void afterAssess(AssessResult assessResult, BaseChainExecutor executor) {
        List<String> extraMethodList = assessResult.getExtraNodeList();
        //动态添加审核节点，同时更新流程生成器和执行者
        if (CollectionUtils.isNotEmpty(extraMethodList)) {
            appendNodeToProvider(assessResult.getProjectId(), extraMethodList);
            appendNodeToExecutor(executor, extraMethodList);
            assessResult.setChain(executor.getAssessNodeList());
        }
        //已解押则审核流程停止
//        if (assessResult.getProjectStatus() == ProjectStatus.COMPLETE) {
//            assessResult.setHasStop(true);
//        }
        //节点跳转时清空缓存
        if (assessResult.getAssessStatus() == AssessStatus.BACKWARD || assessResult.getAssessStatus() == AssessStatus.FROWARD) {
            log.info("[flow] project {} chain go backward to method {},clear cache", assessResult.getProjectId(), assessResult.getMoveTo());
            //ChainProviderCache.getInstance().put(assessResult.getProjectId(), null);
        }
    }
} 
