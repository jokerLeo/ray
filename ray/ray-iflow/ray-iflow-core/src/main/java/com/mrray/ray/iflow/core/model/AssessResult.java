package com.mrray.ray.iflow.core.model;

import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.enums.OrganizationType;
import com.mrray.ray.iflow.core.enums.ProjectStatus;
import lombok.Data;

import java.util.List;

/**
 * 评估结果
 *
 * @author lyc
 * @create 2019-12-13 15:10
 **/
@Data
public class AssessResult {
    private List<AssessNode> chain;
    private boolean hasStop;
    private int currentIndex;
    private Long projectId;
    private String moveTo;
    private String operationDescription;
    private AssessStatus assessStatus;
    private ProjectStatus projectStatus;
    private OrganizationType organizationType;
    private String nodeName;
    private String nodeMethod;
    private List<String> extraNodeList;
    private Object args;
} 
