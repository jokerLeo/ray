package com.mrray.ray.iflow.rpc.service.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * spring容器初始化完成事件
 *
 * @author lyc
 **/
@Component
@Slf4j
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (null == contextRefreshedEvent.getApplicationContext().getParent()) {
            log.info(">>>>> spring初始化完毕 <<<<<");
            Map<String, BaseMapper> baseMapperMap = contextRefreshedEvent.getApplicationContext().getBeansOfType(BaseMapper.class);
            for (Map.Entry entry : baseMapperMap.entrySet()) {
                log.info("mapper init: {}", entry.getKey());
            }
            Map<String, DataSource> dataSourceMap = contextRefreshedEvent.getApplicationContext().getBeansOfType(DataSource.class);
            for (Map.Entry entry : dataSourceMap.entrySet()) {
                log.info("datasource init: {} ", entry.getKey());
            }

        }
    }

}
