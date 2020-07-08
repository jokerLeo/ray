package com.mrray.ray.common.swagger;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * swagger配置
 *
 * @author lyc
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(SwaggerAutoConfig.class)
public @interface EnableSwaggerAutoConfig {
} 
