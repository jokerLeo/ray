package com.mrray.ray.iflow.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评估节点状态，用以展示节点
 *
 * @author lyc
 * @create 2019-12-11 14:43
 **/
@Getter
@AllArgsConstructor
public enum AssessStatus {
    /**
     * 通过
     */
    PASS(1, "通过"),
    /**
     * 终止
     */
    FAIL(2, "终止"),
    /**
     * 评估中
     */
    RUNNING(3, "评估中"),
    /**
     * 等待执行
     */
    WAITING(4, "等待执行"),
    /**
     * 回退
     */
    BACKWARD(5, "回退"),
    /**
     * 跳过
     */
    FROWARD(6, "跳过");

    private int code;
    private String description;

    @Override
    public String toString() {
        return description;
    }
}
