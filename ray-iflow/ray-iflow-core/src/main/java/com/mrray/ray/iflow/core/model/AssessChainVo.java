package com.mrray.ray.iflow.core.model;

import com.mrray.ray.iflow.core.enums.ProjectStatus;
import lombok.Data;

import java.util.List;

/**
 * 评估链视图
 *
 * @author lyc
 * @create 2019-12-18 17:23
 **/
@Data
public class AssessChainVo {
    private List<AssessNode> chain;
    private boolean hasStop;
    private int currentIndex;
    private ProjectStatus projectStatus;
}