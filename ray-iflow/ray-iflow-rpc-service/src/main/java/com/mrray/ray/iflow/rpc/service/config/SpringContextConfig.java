package com.mrray.ray.iflow.rpc.service.config;

import com.mrray.ray.common.springboot.EncryptPropertyPlaceholderConfigurer;
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

    /**
     * 配置自定义yml文件加解密
     *
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        PropertySourcesPlaceholderConfigurer config = new EncryptPropertyPlaceholderConfigurer();
        //设置需要解密的配置文件
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application-dev.yml"));
        config.setProperties(yaml.getObject());
        return config;
    }
}
