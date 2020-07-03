package com.mrray.ray.iflow.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 组织类型
 *
 * @author lyc
 * @create 2019-12-12 10:36
 **/
@Getter
@AllArgsConstructor
public enum OrganizationType {
    /**
     * 企业
     */
    ENTERPRISE(1, "企业"),
    /**
     * 银行
     */
    BANK(2, "银行"),
    /**
     * 评估机构
     */
    ASSESSMENT(3, "评估机构"),
    /**
     * 平台运营
     */
    ADMINISTER(4, "平台运营"),
    /**
     * 知识产权局
     */
    INTELLECTUAL_PROPERTY_OFFICE(5, "知识产权局"),
    /**
     * 知识产权促进中心
     */
    INTELLECTUAL_PROPERTY_CENTER(6, "知识产权促进中心");

    private int code;
    private String description;

    @Override
    public String toString() {
        return description;
    }
} 
