package com.mrray.ray.iflow.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评估链发起者类型
 *
 * @author lyc
 * @create 2019-12-12 15:57
 **/
@Getter
@AllArgsConstructor
public enum ChainProviderRole {
    /**
     * 默认的流程开头
     */
    DEFAULT(1, "默认的流程开头");

    private int code;
    private String description;

    @Override
    public String toString() {
        return description;
    }
} 
