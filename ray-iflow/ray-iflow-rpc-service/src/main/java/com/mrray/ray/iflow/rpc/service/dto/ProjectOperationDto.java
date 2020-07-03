package com.mrray.ray.iflow.rpc.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lyc
 * @version 1.0
 * @description 项目审核操作状态记录分析对象
 * @createDate 2020/5/13 10:20
 **/
@Data
@ApiModel(value = "项目审核操作状态记录分析对象")
public class ProjectOperationDto {
    @ApiModelProperty(notes = "项目id")
    private Long projectId;
    @ApiModelProperty(notes = "项目最大通过状态")
    private Integer maxPassStatus;
    @ApiModelProperty(notes = "项目起始或停止的状态")
    private Integer startOrStopStatus;
}
