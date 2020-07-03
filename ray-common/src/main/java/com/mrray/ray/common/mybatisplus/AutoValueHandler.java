package com.mrray.ray.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 处理新增和更新的基础数据填充
 *
 * @author lyc
 **/
public class AutoValueHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //SnowflakeIdWorker idWorker0 = new SnowflakeIdWorker(0, 0);
        //setInsertFieldValByName("id", idWorker0.nextId(), metaObject);
        setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName("updateTime", new Date(), metaObject);
    }
} 
