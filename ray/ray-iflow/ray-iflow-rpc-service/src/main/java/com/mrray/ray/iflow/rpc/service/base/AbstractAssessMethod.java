package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.iflow.core.control.ButtonControl;
import com.mrray.ray.iflow.core.control.ControlUtils;
import com.mrray.ray.iflow.core.exception.ChainException;
import com.mrray.ray.iflow.core.method.AssessMethod;
import com.mrray.ray.iflow.core.model.AssessProject;
import com.mrray.ray.iflow.core.model.NodeResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 抽象评估方法
 *
 * @author lyc
 **/
@Slf4j
public abstract class AbstractAssessMethod implements AssessMethod {

    /**
     * 执行审核操作前的数据初始化
     */
    @Override
    public void init() {
        //ignore
    }

    /**
     * 确定时执行的操作
     *
     * @return
     */
    @Override
    public NodeResult doConfirm() {
        throw new ChainException("未实现确认操作");
    }

    /**
     * 无法判断确认还是拒绝时的默认处理，用于项目创建，申请金融产品等材料提交操作
     *
     * @return
     */
    @Override
    public NodeResult doDefault() {
        throw new ChainException("未实现默认操作");
    }

    protected AssessProject getAssessProject() {
        return DefaultMethodExecutor.getAssessProject();
    }

    /**
     * 拒绝时执行的操作
     *
     * @return
     */
    @Override
    public NodeResult doRefuse() {
        throw new ChainException("未实现拒绝操作");
    }

    /**
     * 获取确认参数
     *
     * @param tClass
     * @param <T>
     * @return
     */
    protected <T> T getPassArgs(Class<T> tClass) {
        return ControlUtils.getConfirmArgs(DefaultMethodExecutor.getAssessProject(), tClass);
    }

    /**
     * 获取拒绝参数
     *
     * @return
     */
    protected <T> T getStopArgs(Class<T> tClass) {
        return ControlUtils.getStopArgs(DefaultMethodExecutor.getAssessProject(), tClass);
    }

    /**
     * 节点控件相关参数，用于前端生成视图
     *
     * @return
     */
    List<ButtonControl> getExtraArgument() {
        return Arrays.asList(
                ControlUtils.confirmBtn("确定"),
                ControlUtils.stopBtn("拒绝")
        );
    }
} 
