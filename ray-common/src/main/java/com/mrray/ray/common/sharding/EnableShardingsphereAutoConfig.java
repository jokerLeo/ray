package com.mrray.ray.common.sharding;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 * 自动配置shardingsphere
 *
 * @author lyc
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(MyShardingsphereConfig.class)
public @interface EnableShardingsphereAutoConfig {
} 
