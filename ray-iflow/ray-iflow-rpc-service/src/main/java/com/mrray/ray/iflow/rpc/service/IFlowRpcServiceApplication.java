package com.mrray.ray.iflow.rpc.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.mrray.ray.common.sharding.EnableShardingsphereAutoConfig;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 服务
 *
 * @author lyc
 **/
@EnableShardingsphereAutoConfig
@EnableDubboConfiguration
@EnableTransactionManagement
@SpringBootApplication(exclude = {SpringBootConfiguration.class, DataSourceAutoConfiguration.class})
public class IFlowRpcServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IFlowRpcServiceApplication.class, args);
    }
} 
