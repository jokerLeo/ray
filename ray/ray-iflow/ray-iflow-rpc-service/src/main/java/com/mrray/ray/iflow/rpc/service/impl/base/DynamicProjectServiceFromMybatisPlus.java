package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Flow;
import com.mrray.ray.iflow.rpc.api.base.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试基于mybatisplus的动态数据源
 *
 * @author lyc
 **/
@Service
public class DynamicProjectServiceFromMybatisPlus {
    @Autowired
    private IFlowService flowService;

    //@DS("master")
    public Flow getByIdFromMaster(Long id) {
        return flowService.getById(id);
    }

    //@DS("slave_1")
    public boolean updateSlave1FlowName(String name) {
        Flow flow = flowService.getById(1L);
        flow.setName(name);
        return flowService.updateById(flow);
    }

    //@DS("slave_1")
    public Flow getByIdFromSlave1(Long id) {
        return flowService.getById(id);
    }

    //@DS("slave_2")
    public Flow getByIdFromSlave2(Long id) {
        return flowService.getById(id);
    }
} 
