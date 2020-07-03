package com.mrray.ray.iflow.rpc.service.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.mrray.ray.common.mybatisplus.AutoValueHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Plus 配置
 *
 * @author lyc
 **/
@Configuration
@MapperScan("com.mrray.ray.iflow.dao.base.mapper")
public class MybatisPlusConfig {

    /**
     * 配置属性自动填充
     *
     * @return
     */
    @Bean

    public MetaObjectHandler metaObjectHandler() {
        return new AutoValueHandler();
    }

    /**
     * 全局配置
     *
     * @return
     */
    @Bean
    public GlobalConfig autoValue(MetaObjectHandler metaObjectHandler) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(metaObjectHandler);
        return globalConfig;
    }

    /**
     * SQL执行效率插件,生产环境不建议使用
     *
     * @return
     */
    //@Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
