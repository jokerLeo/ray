package com.mrray.ray.iflow.core.model;

import com.mrray.ray.iflow.core.control.ButtonControl;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 待评估的项目，在评估链中会修改相应状态
 *
 * @author lyc
 * @create 2019-12-12 15:24
 **/
@Data
public class AssessProject {
    @NotNull(message = "项目不能为空")
    private Long projectId;
    private List<ButtonControl> buttonControls;
} 
