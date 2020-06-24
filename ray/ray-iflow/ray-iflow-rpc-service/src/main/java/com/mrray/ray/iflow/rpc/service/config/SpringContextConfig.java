package com.mrray.ray.iflow.rpc.service.config;

import com.mrray.ray.common.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring配置
 *
 * @author lyc
 **/
@Configuration
public class SpringContextConfig {

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
