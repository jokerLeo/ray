package com.mrray.ray.iflow.rpc.service.session;

import com.mrray.ray.iflow.rpc.service.Configuration;
import com.mrray.ray.iflow.rpc.service.base.DefaultAssessSessionFactory;

/**
 * 评估会话构建器
 *
 * @author lyc
 * @create 2020-03-10 15:46
 **/
public class AssessSessionFactoryBuilder {
    public AssessSessionFactory build(Configuration configuration) {
        return new DefaultAssessSessionFactory(configuration);
    }
} 
