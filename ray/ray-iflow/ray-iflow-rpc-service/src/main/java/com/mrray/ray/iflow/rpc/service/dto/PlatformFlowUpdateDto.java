package com.mrray.ray.iflow.rpc.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 */
@Data
@ApiModel(value = "平台更新流程参数对象")
public class PlatformFlowUpdateDto {
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 图片路径
     */
    @ApiModelProperty("图片路径")
    private String image;

    /**
     * 流程名称
     */
    @ApiModelProperty("流程名称")
    private String name;


    /**
     * 平台审核状态
     */
    @ApiModelProperty("平台审核状态")
    private Integer examineStatus;


}
