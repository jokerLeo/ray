package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.model.AssessChainVo;
import com.mrray.ray.iflow.core.model.AssessNode;
import org.apache.commons.lang3.SerializationUtils;

/**
 * 抽象评估链适配器
 *
 * @author lyc
 **/
public abstract class AbstractChainProviderAdapter {
    private BaseChainExecutor provider;

    public AbstractChainProviderAdapter(BaseChainExecutor baseChainExecutor) {
        provider = SerializationUtils.clone(baseChainExecutor);
        for (AssessNode node : provider.getAssessNodeList()) {
            resetButtonControls(node);
        }
        resetAssessNodeList(provider);
    }

    private boolean isStop() {
        return provider.isStop();
    }

    private int getCurrentIndex() {
        return provider.getCurrentIndex();
    }

    AssessChainVo build() {
        AssessChainVo assessChainVo = new AssessChainVo();
        assessChainVo.setHasStop(isStop());
        assessChainVo.setCurrentIndex(getCurrentIndex());
        assessChainVo.setChain(provider.getAssessNodeList());
        return assessChainVo;
    }

    /**
     * 根据需要重置评估流程节点
     *
     * @param provider
     */
    abstract void resetAssessNodeList(BaseChainExecutor provider);

    /**
     * 根据需要重写此方法以按角色重置button或节点状态
     */
    private void resetButtonControls(AssessNode node) {
        //已经通过或终止处的节点不展示控件信息
        if (node.getAssessStatus() == AssessStatus.PASS || node.getAssessStatus() == AssessStatus.FAIL) {
            node.setButtonControls(null);
            return;
        }
        //等待中的节点也不展示控件信息
        if (getCurrentIndex() < provider.getAssessNodeList().indexOf(node)) {
            node.setButtonControls(null);
            return;
        }
        //检查所需权限
        if (!withRole(node) || !withPermission(node)) {
            node.setButtonControls(null);
        }
    }

    /**
     * 是否是相应角色
     *
     * @param node
     * @return
     */
    private boolean withRole(AssessNode node) {
        //TODO 需要子类重写，否则默认返回true
        return true;
    }

    /**
     * 是否有权限
     *
     * @param node
     * @return
     */
    private boolean withPermission(AssessNode node) {
        //TODO 需要子类重写，否则默认返回true
        return true;
    }
} 
