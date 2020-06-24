package com.mrray.ray.iflow.admin;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * IFlowAdminApplication
 *
 * @author lyc
 **/
@EnableDubboConfiguration
@SpringBootApplication
public class IFlowAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(IFlowAdminApplication.class, args);
    }
} 
