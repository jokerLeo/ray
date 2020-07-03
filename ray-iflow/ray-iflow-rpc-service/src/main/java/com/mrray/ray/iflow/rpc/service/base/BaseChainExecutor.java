package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.common.SpringContextUtil;
import com.mrray.ray.iflow.core.ChainExecutor;
import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.exception.ChainException;
import com.mrray.ray.iflow.core.method.MethodExecutor;
import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.AssessResult;
import com.mrray.ray.iflow.core.model.NodeResult;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 评估链执行者
 *
 * @author lyc
 * @create 2019-12-12 16:26
 **/
@Slf4j
public class BaseChainExecutor implements ChainExecutor, Serializable {
    @Setter
    @Getter
    private List<AssessNode> assessNodeList;

    public BaseChainExecutor() {
        assessNodeList = new LinkedList<>();
    }

    public int getSize() {
        return CollectionUtils.isEmpty(assessNodeList) ? 0 : assessNodeList.size();
    }

    public boolean isStop() {
        return getCurrent() == null;
    }

    @Override
    public AssessResult execute(AssessProject assessProject) {
        if (isStop()) {
            throw new ChainException("该项目审核流程已终止，无法再进行审核");
        }

        AssessResult assessResult = new AssessResult();
        AssessNode assessNode = getCurrentNode();
        if (assessNode != null) {
            if (!permissionCheck(assessNode)) {
                throw new ChainException("无操作权限");
            }

            AssessSessionFactory container = SpringContextUtil.getBean(AssessSessionFactory.class);
            MethodExecutor executor = container.getConfiguration().newMethodExecutor();

            NodeResult nodeResult = executor.assess(assessProject, assessNode.getMethodKey());
            if (null == nodeResult) {
                throw new ChainException("审核操作未返回任何结果");
            }
            BeanUtils.copyProperties(nodeResult, assessResult);
            assessNode.setAssessStatus(nodeResult.getAssessStatus());
            assessResult.setProjectId(assessProject.getProjectId());
            assessResult.setCurrentIndex(getCurrentIndex());
            assessResult.setNodeName(assessNode.getDescription());
            assessResult.setNodeMethod(assessNode.getMethodKey());

            move(assessResult);
        }

        return assessResult;
    }

    /**
     * 添加评估节点
     *
     * @param assessNode
     */
    public void addChainNode(AssessNode assessNode) {
        if (assessNode == null) {
            return;
        }
        AssessNode node = findByMethodKey(assessNode.getMethodKey());
        if (node == null) {
            assessNodeList.add(assessNode);
        } else {
            log.warn("处理方法为{}的节点已存在", node.getMethodKey());
        }
    }

    /**
     * 在某一评估节点前添加节点
     *
     * @param assessNode
     * @param methodKey
     */
    public void addBeforeByMethodClass(AssessNode assessNode, String methodKey) {
        if (assessNode == null) {
            return;
        }
        int index = findIndexByMethodKey(methodKey) - 1;

        if (index < getCurrentIndex()) {
            log.info("不能在已处理节点之间添加评估节点");
            return;
        }

        if (index == -1) {
            assessNodeList.add(0, assessNode);
        } else {
            assessNodeList.add(index, assessNode);
        }
    }

    /**
     * 在某一评估节点后添加节点
     *
     * @param assessNode
     * @param methodKey
     */
    public void addAfterByMethodClass(AssessNode assessNode, String methodKey) {
        int index = findIndexByMethodKey(methodKey) + 1;
        assessNodeList.add(index, assessNode);
    }

    /**
     * 保存关键字数组对应的节点
     *
     * @param methodKey
     */
    public void keepByMethodKey(String... methodKey) {
        if (methodKey == null) {
            return;
        }

        List<AssessNode> removeNodeList = new ArrayList<>();
        for (AssessNode node : assessNodeList) {
            boolean needRemove = Arrays.stream(methodKey).filter(s -> s.equalsIgnoreCase(node.getMethodKey())).count() == 0;
            if (needRemove) {
                removeNodeList.add(findByMethodKey(node.getMethodKey()));
            }
        }

        assessNodeList.removeAll(removeNodeList);
    }

    /**
     * 移除节点
     *
     * @param methodKeys
     */
    public void removeByMethodKey(String... methodKeys) {
        for (String methodKey : methodKeys) {
            int index = findIndexByMethodKey(methodKey);
            if (-1 == index) {
                continue;
            }
            assessNodeList.remove(index);
        }
    }

    /**
     * 获取当前节点的索引
     *
     * @return
     */
    public int getCurrentIndex() {
        Optional failNode = assessNodeList.stream().filter(s -> s.getAssessStatus() == AssessStatus.FAIL).findFirst();
        if (failNode.isPresent()) {
            return assessNodeList.indexOf(failNode.get());
        }

        return assessNodeList.indexOf(getCurrent());
    }

    /**
     * 节点移动
     *
     * @param assessResult
     */
    private void move(AssessResult assessResult) {
        if (assessResult.getAssessStatus() == AssessStatus.BACKWARD) {
            backward(assessResult.getMoveTo());
        } else if (assessResult.getAssessStatus() == AssessStatus.FROWARD) {
            if (null != getCurrent()) {
                forward(getCurrent().getMethodKey(), assessResult.getMoveTo());
            }
        } else if (assessResult.getAssessStatus() == AssessStatus.FAIL) {
            fail();
        } else if (assessResult.getAssessStatus() == AssessStatus.RUNNING) {
            running();
        } else {
            waiting();
        }

    }


    /**
     * 回退操作
     *
     * @param moveTo
     */
    private void backward(String moveTo) {
        AssessNode moveToNode = getAssessNodeList().stream().
                filter(s -> s.getMethodKey().equalsIgnoreCase(moveTo)).findFirst().get();
        for (int i = 0; i < getAssessNodeList().size(); i++) {
            if (i < getAssessNodeList().indexOf(moveToNode)) {
                getAssessNodeList().get(i).setAssessStatus(AssessStatus.PASS);
            } else {
                getAssessNodeList().get(i).setAssessStatus(AssessStatus.WAITING);
            }
        }
    }

    /**
     * 向前跳过操作
     *
     * @param moveTo
     */
    private void forward(String currentNodeKey, String moveTo) {
        AssessNode moveToNode = getAssessNodeList().stream().
                filter(s -> s.getMethodKey().equalsIgnoreCase(moveTo)).findFirst().get();
        for (int i = 0; i < getAssessNodeList().size(); i++) {
            if (i <= getAssessNodeList().indexOf(currentNodeKey)) {
                getAssessNodeList().get(i).setAssessStatus(AssessStatus.PASS);
            } else if (i < getAssessNodeList().indexOf(moveToNode)) {
                getAssessNodeList().get(i).setAssessStatus(AssessStatus.FROWARD);
            } else {
                getAssessNodeList().get(i).setAssessStatus(AssessStatus.WAITING);
            }
        }
    }

    /**
     * 正常流程初始化处理
     *
     * @param moveTo
     * @param assessStatus
     */
    public void pass(String moveTo, AssessStatus assessStatus) {
        AssessNode currentNode = getAssessNodeList().stream().
                filter(s -> s.getMethodKey().equalsIgnoreCase(moveTo)).findFirst().get();
        currentNode.setAssessStatus(assessStatus);

        for (int i = 0; i < getAssessNodeList().indexOf(currentNode); i++) {
            getAssessNodeList().get(i).setAssessStatus(AssessStatus.PASS);
        }
    }

    private void fail() {
        //ignore
    }

    private void running() {
        //ignore
    }

    private void waiting() {
        //ignore
    }

    private AssessNode getCurrentNode() {
        return getCurrent();
    }

    /**
     * 获取当前节点
     *
     * @return
     */
    private AssessNode getCurrent() {
        if (CollectionUtils.isEmpty(assessNodeList)) {
            return null;
        }

        Optional failNode = assessNodeList.stream().filter(s -> s.getAssessStatus() == AssessStatus.FAIL).findFirst();
        if (failNode.isPresent()) {
            return null;
        }

        for (AssessNode node : assessNodeList) {
            if (node.getAssessStatus() == AssessStatus.WAITING ||
                    node.getAssessStatus() == AssessStatus.RUNNING) {
                return node;
            }
        }

        return null;
    }

    private AssessNode findByMethodKey(String methodKey) {
        if (StringUtils.isBlank(methodKey)) {
            return null;
        }
        Optional<AssessNode> optional = assessNodeList.stream().filter(s -> s.getMethodKey().equalsIgnoreCase(methodKey)).findFirst();

        return optional.isPresent() ? optional.get() : null;
    }

    private int findIndexByMethodKey(String methodKey) {
        AssessNode assessNode = findByMethodKey(methodKey);
        if (assessNode == null) {
            return -1;
        }
        return assessNodeList.indexOf(assessNode);
    }
} 
