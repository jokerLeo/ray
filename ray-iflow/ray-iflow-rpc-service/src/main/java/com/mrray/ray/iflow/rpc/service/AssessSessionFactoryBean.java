package com.mrray.ray.iflow.rpc.service;

import com.mrray.ray.iflow.core.interceptor.AssessInterceptor;
import com.mrray.ray.iflow.rpc.service.base.AbstractAssessMethod;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.stream.Stream;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 评估会话工厂
 *
 * @author lyc
 * @create 2020-03-10 15:34
 **/
@Slf4j
@Data
public class AssessSessionFactoryBean implements FactoryBean<AssessSessionFactory>, InitializingBean {
    private AssessSessionFactory assessSessionFactory;
    private AssessInterceptor[] interceptors;
    private AbstractAssessMethod[] assessMethods;
    private AssessSessionFactoryBuilder assessSessionFactoryBuilder = new AssessSessionFactoryBuilder();
    private Configuration configuration;

    @Override
    public AssessSessionFactory getObject() throws Exception {
        if (null == assessSessionFactory) {
            afterPropertiesSet();
        }

        return assessSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return assessSessionFactory == null ? AssessSessionFactory.class : assessSessionFactory.getClass();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setAssessMethods(assessMethods);
        if (!isEmpty(interceptors)) {
            Stream.of(interceptors).forEach(plugin -> {
                configuration.addInterceptor(plugin);
                log.info("[flow] add plugin: '" + plugin + "'");
            });
        }
        assessSessionFactory = assessSessionFactoryBuilder.build(configuration);
    }
}
