package com.mrray.ray.iflow.rpc.service;

import com.mrray.ray.iflow.core.interceptor.AssessInterceptor;
import com.mrray.ray.iflow.rpc.service.base.AbstractAssessMethod;
import com.mrray.ray.iflow.rpc.service.session.AssessSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 评估流程自动配置
 *
 * @author lyc
 * @create 2019-12-12 14:11
 **/
@Configuration
public class AssessFlowAutoConfiguration implements InitializingBean {

    private AssessInterceptor[] interceptors;

    private AbstractAssessMethod[] assessMethods;

    public AssessFlowAutoConfiguration(ObjectProvider<AssessInterceptor[]> interceptorsProvider,
                                       ObjectProvider<AbstractAssessMethod[]> abstractAssessMethodProvider) {
        interceptors = interceptorsProvider.getIfAvailable();
        assessMethods = abstractAssessMethodProvider.getIfAvailable();
    }

    @Bean
    public AssessSessionFactory getAssessSessionFactory() throws Exception {
        AssessSessionFactoryBean assessSessionFactoryBean = new AssessSessionFactoryBean();
        assessSessionFactoryBean.setInterceptors(interceptors);
        assessSessionFactoryBean.setAssessMethods(assessMethods);
        return assessSessionFactoryBean.getObject();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
