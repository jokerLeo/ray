package com.mrray.ray.iflow.core.enums;

import lombok.Getter;

/**
 * @author lyc
 * @version 1.0
 * @description 评估链类型
 * @createDate 2019/12/12 16:23
 **/
@Getter
public enum ChainType {
    /**
     * 默认的评估链类型
     */
    DEFAULT(1, "默认的评估链类型");

    private int code;
    private String description;

    ChainType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
