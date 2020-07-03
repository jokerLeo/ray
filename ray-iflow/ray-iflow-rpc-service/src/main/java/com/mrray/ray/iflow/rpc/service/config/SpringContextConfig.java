package com.mrray.ray.iflow.rpc.service.config;

import com.mrray.ray.common.SpringContextUtil;
import com.mrray.ray.common.plugin.EncryptPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * spring配置
 *
 * @author lyc
 **/
@Configuration
public class SpringContextConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        PropertySourcesPlaceholderConfigurer config = new EncryptPropertyPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application-dev.yml"));
        config.setProperties(yaml.getObject());
        return config;
    }

    /**
     * 配置属性自动填充
     *
     * @return
     */
    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }
}
