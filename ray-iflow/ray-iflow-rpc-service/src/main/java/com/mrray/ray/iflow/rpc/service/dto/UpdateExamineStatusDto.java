package com.mrray.ray.iflow.rpc.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "流程更新为启用或者停用对象")
public class UpdateExamineStatusDto {
    @ApiModelProperty(notes = "流程id")
    private Long id;
    @ApiModelProperty("平台审核状态")
    private int examineStatus;
}
