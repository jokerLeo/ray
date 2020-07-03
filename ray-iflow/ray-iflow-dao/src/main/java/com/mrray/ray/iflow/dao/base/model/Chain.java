package com.mrray.ray.iflow.dao.base.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lyc
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Chain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    //@TableField(fill = FieldFill.INSERT)
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

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
