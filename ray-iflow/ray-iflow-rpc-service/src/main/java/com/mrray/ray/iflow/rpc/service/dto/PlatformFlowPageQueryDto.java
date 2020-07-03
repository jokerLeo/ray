package com.mrray.ray.iflow.rpc.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "平台查询流程分页查询对象")
public class PlatformFlowPageQueryDto extends PageQueryDto {
    @ApiModelProperty(notes = "流程id或名称")
    private String idOrName;
    @ApiModelProperty("平台审核状态")
    private String examineStatus;
}
