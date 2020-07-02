package com.mrray.ray.common.sharding;

import com.mrray.ray.common.key.SnowflakeIdWorker;
import io.shardingsphere.core.keygen.KeyGenerator;

/**
 * 用于sharding-jdbc生成主键的雪花算法
 *
 * @author lyc
 **/
public class SnowflakeShardingKeyGenerator implements KeyGenerator {
    @Override
    public Number generateKey() {
        SnowflakeIdWorker idWorker0 = new SnowflakeIdWorker(0, 0);
        return idWorker0.nextId();
    }
}
