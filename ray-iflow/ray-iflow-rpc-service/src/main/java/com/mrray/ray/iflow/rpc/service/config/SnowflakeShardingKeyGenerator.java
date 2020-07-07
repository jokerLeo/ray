package com.mrray.ray.iflow.rpc.service.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * 自定义主键生成算法
 *
 * @author lyc
 **/
public class SnowflakeShardingKeyGenerator implements ShardingKeyGenerator {
    @Override
    public Comparable<?> generateKey() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        Long id = snowflake.nextId();
        return id;
    }

    @Override
    public String getType() {
        return "SnowflakeShardingKeyGenerator";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
} 
