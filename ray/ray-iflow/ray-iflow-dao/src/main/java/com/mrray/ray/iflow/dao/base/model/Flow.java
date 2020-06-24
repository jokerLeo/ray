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
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    @TableField(fill = FieldFill.INSERT)
    private Long id;

    /**
     * 链名
     */
    private String name;

    /**
     * 图片路径
     */
    private String image;

    /**
     * 描述
     */
    private String description;

    /**
     * 参与方角色配置
     */
    private String organizationList;

    /**
     * 参与方数量
     */
    private Integer memberCount;

    /**
     * 节点数量
     */
    private Integer nodeCount;

    /**
     * 评估链方法集合
     */
    private String chainMethodList;

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
