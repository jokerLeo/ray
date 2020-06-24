package com.mrray.ray.iflow.rpc.service.dto;

import lombok.Data;

@Data
public class FlowPageQueryDto extends PageQueryDto {
    private String idOrName;
    private String examineStatus;
}
