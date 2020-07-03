package com.mrray.ray.iflow.core.control;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 按钮控件模型，用于判断前端按钮的点击和参数传递
 *
 * @author lyc
 * @create 2019-12-12 11:22
 **/
@Data
public class ButtonControl implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 控件名称
     */
    private String name;
    /**
     * 可通过关键字获取相应的参数
     */
    private String key;
    /**
     * 是否点击
     */
    private Boolean value;
    /**
     * 参数值，可以是自定义dto对象，需要解析
     */
    private Object args;
    /**
     * 子控件
     */
    private List<ButtonControl> children;

    ButtonControl(String name, String key) {
        this.name = name;
        this.key = key;
        value = false;
    }
} 
