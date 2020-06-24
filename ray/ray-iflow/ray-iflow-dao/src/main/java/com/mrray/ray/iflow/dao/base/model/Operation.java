package com.mrray.ray.iflow.dao.base.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;

/**
 * 
 *
 * @author lyc
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作状态
     */
    private Integer assessStatus;

    /**
     * 项目状态
     */
    private Integer projectStatus;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 参数json字符串
     */
    private String args;

    /**
     * 操作描述，根据业务人为设定
     */
    private String operationDescription;

    /**
     * 评估节点名称
     */
    private String nodeName;

    /**
     * 当前节点关键字
     */
    private String nodeMethod;

    /**
     * 跳转到的节点关键字
     */
    private String moveMethod;

    /**
     * 组织类型
     */
    private Integer organizationType;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
