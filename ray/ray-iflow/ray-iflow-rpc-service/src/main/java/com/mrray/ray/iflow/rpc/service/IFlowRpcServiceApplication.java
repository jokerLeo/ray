package com.mrray.ray.iflow.rpc.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import io.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 服务
 *
 * @author lyc
 **/
@SpringBootApplication(exclude = {SpringBootConfiguration.class, DataSourceAutoConfiguration.class})
@EnableDubboConfiguration
public class IFlowRpcServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IFlowRpcServiceApplication.class, args);
    }
} 
