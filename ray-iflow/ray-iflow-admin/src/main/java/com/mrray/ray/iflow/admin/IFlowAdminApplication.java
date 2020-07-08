package com.mrray.ray.iflow.admin;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.mrray.ray.common.swagger.EnableSwaggerAutoConfig;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * IFlowAdminApplication
 *
 * @author lyc
 **/
@EnableDubboConfiguration
@SpringBootApplication(exclude = {SpringBootConfiguration.class, DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@EnableSwaggerAutoConfig
public class IFlowAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(IFlowAdminApplication.class, args);
    }
} 
