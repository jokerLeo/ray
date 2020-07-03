package com.mrray.ray.iflow.core.model;

import com.mrray.ray.iflow.core.control.ButtonControl;
import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.enums.OrganizationType;
import com.mrray.ray.iflow.core.enums.ProjectStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 评估节点
 *
 * @author lyc
 **/
@Data
@NoArgsConstructor
public class AssessNode implements Serializable {
    private static final long serialVersionUID = 1L;

    private String methodKey;
    private String description;
    private String iconUrl;
    private OrganizationType organizationType;
    private ProjectStatus projectStatus;
    private AssessStatus assessStatus;
    private List<ButtonControl> buttonControls;
}
