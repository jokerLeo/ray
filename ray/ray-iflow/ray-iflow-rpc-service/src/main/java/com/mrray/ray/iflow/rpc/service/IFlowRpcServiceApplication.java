package com.mrray.ray.iflow.rpc.service;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务
 *
 * @author lyc
 **/
@SpringBootApplication
@EnableDubboConfiguration
public class IFlowRpcServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IFlowRpcServiceApplication.class, args);
    }
} 
