package com.mrray.ray.iflow.rpc.service.base;

import com.mrray.ray.iflow.core.enums.AssessStatus;
import com.mrray.ray.iflow.core.model.AssessNode;
import com.mrray.ray.iflow.rpc.service.Configuration;
import com.mrray.ray.iflow.rpc.service.annotation.Assessor;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 默认评估会话
 *
 * @author lyc
 * @create 2020-03-10 15:47
 **/
public class DefaultAssessSessionFactory implements AssessSessionFactory {
    private Configuration configuration;

    @Getter
    private List<AssessNode> assessNodeList;

    public DefaultAssessSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @PostConstruct
    public void init() {
        assessNodeList = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(configuration.getAssessMethods())) {
            for (AbstractAssessMethod entry : configuration.getAssessMethods()) {
                Assessor assessor = entry.getClass().getAnnotation(Assessor.class);
                if (null != assessor) {
                    AssessNode assessNode = new AssessNode();
                    assessNode.setDescription(assessor.nodeName());
                    assessNode.setIconUrl(assessor.iconUrl());
                    assessNode.setMethodKey(entry.getClass().getSimpleName());
                    assessNode.setAssessStatus(AssessStatus.WAITING);
                    assessNode.setButtonControls(entry.getExtraArgument());
                    assessNode.setOrganizationType(assessor.organization());

                    assessNodeList.add(assessNode);
                }
            }
            configuration.setAssessNodeList(assessNodeList);
        }
    }

    @Override
    public AssessNode get(String methodKey) {
        for (AssessNode assessNode : assessNodeList) {
            if (assessNode.getMethodKey().equalsIgnoreCase(methodKey)) {
                return SerializationUtils.clone(assessNode);
            }
        }

        return null;
    }

    @Override
    public AbstractAssessMethod getMethod(String methodKey) {
        if (StringUtils.isNotBlank(methodKey) && ArrayUtils.isNotEmpty(configuration.getAssessMethods())) {
            Optional<AbstractAssessMethod> method = Arrays.stream(configuration.getAssessMethods()).
                    filter(s -> methodKey.equalsIgnoreCase(s.getClass().getSimpleName())).findFirst();

            return method.isPresent() ? method.get() : null;
        }

        return null;
    }
}
