package com.mrray.ray.iflow.core.method;

import com.mrray.ray.iflow.core.model.NodeResult;

import java.io.Serializable;

/**
 * 评估方法接口
 *
 * @author lyc
 * @create 2019-12-12 13:58
 **/
public interface AssessMethod extends Serializable {

    /**
     * 执行审核操作前的数据初始化
     */
    void init();

    /**
     * 确定时执行的操作
     *
     * @return
     */
    NodeResult doConfirm();

    /**
     * 无法判断确认还是拒绝时的默认处理，用于申请金融产品等材料提交操作
     *
     * @return
     */
    NodeResult doDefault();

    /**
     * 拒绝时执行的操作
     *
     * @return
     */
    NodeResult doRefuse();
} 
