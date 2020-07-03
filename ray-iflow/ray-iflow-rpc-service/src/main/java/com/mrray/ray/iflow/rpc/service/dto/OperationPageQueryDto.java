package com.mrray.ray.iflow.rpc.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lyc
 * @version 1.0
 * @description 评估操作分页查询对象
 * @createDate 2019/12/17 17:12
 **/
@Data
@ApiModel(value = "评估操作分页查询对象")
public class OperationPageQueryDto extends PageQueryDto {
    @ApiModelProperty(notes = "项目id")
    private Long projectId;
    @ApiModelProperty(notes = "企业id")
    private Long enterpriseId;
    @ApiModelProperty(notes = "银行id")
    private Long bankId;
    @ApiModelProperty(notes = "评估机构id")
    private Long assessmentId;
    @ApiModelProperty(notes = "金融产品id")
    private Long financialId;
    @ApiModelProperty(notes = "项目状态")
    private Integer projectStatus;
    @ApiModelProperty(notes = "组织类型")
    private Integer organizationType;
}
