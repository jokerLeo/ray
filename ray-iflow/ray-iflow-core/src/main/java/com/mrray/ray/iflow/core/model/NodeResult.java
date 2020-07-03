package com.mrray.ray.iflow.core.model;

import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.enums.ProjectStatus;
import lombok.Data;

import java.util.List;

/**
 * 评估节点的评估结果
 *
 * @author lyc
 * @create 2019-12-16 9:39
 **/
@Data
public class NodeResult {
    /**
     * 节点的评估结果
     */
    private AssessStatus assessStatus;
    /**
     * 项目当前的状态
     */
    private ProjectStatus projectStatus;

    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 操作的描述
     */
    private String operationDescription;
    /**
     * 根据业务判断需要在评估链尾添加的评估节点
     * <p>
     * 注：在评估通过的情况下才添加额外节点，以避免重复添加
     */
    private List<String> extraNodeList;
    /**
     * 回退或向前跳过到的目标节点
     */
    private String moveTo;
    /**
     * 需要持久化的节点操作参数
     */
    private Object args;

    public NodeResult(AssessStatus assessStatus, String operationDescription, ProjectStatus projectStatus) {
        this.assessStatus = assessStatus;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
    }

    public NodeResult() {
        assessStatus = AssessStatus.RUNNING;
    }

    /**
     * 节点还需要继续评估
     */
    public NodeResult running(String operationDescription, ProjectStatus projectStatus) {
        assessStatus = AssessStatus.RUNNING;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
        return this;
    }

    /**
     * 评估通过
     */
    private NodeResult pass(String operationDescription, ProjectStatus projectStatus) {
        assessStatus = AssessStatus.PASS;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
        return this;
    }

    /**
     * 评估通过
     */
    public NodeResult pass(String operationDescription, ProjectStatus projectStatus, Object args) {
        pass(operationDescription, projectStatus);
        this.args = args;
        return this;
    }

    /**
     * 评估未通过，回退到之前的步骤
     */
    public NodeResult backward(String operationDescription, ProjectStatus projectStatus, String moveTo) {
        assessStatus = AssessStatus.BACKWARD;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
        this.moveTo = moveTo;
        return this;
    }

    /**
     * 评估未通过，回退到之前的步骤
     */
    public NodeResult backward(String operationDescription, ProjectStatus projectStatus, String moveTo, Object args) {
        assessStatus = AssessStatus.BACKWARD;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
        this.moveTo = moveTo;
        this.args = args;
        return this;
    }

    /**
     * 评估通过，且跳过若干步骤
     */
    public NodeResult forward(String operationDescription, ProjectStatus projectStatus, String moveTo) {
        assessStatus = AssessStatus.FROWARD;
        this.operationDescription = operationDescription;
        this.projectStatus = projectStatus;
        this.moveTo = moveTo;
        return this;
    }
} 
