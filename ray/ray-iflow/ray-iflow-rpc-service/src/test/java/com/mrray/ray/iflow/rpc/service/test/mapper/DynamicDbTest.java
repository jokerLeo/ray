package com.mrray.ray.iflow.rpc.service.test.mapper;

import com.mrray.ray.iflow.dao.base.model.Flow;
import com.mrray.ray.iflow.rpc.api.base.IFlowService;
import com.mrray.ray.iflow.rpc.service.IFlowRpcServiceApplication;
import com.mrray.ray.iflow.rpc.service.impl.base.DynamicProjectServiceFromMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * DynamicDbTest
 *
 * @author lyc
 **/
@SpringBootTest(classes = IFlowRpcServiceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class DynamicDbTest {
    @Autowired
    private IFlowService flowService;

    @Autowired
    private DynamicProjectServiceFromMybatisPlus dynamicProjectServiceFromMybatisPlus;

    @Test
    public void testGetById() {
        Flow flow = flowService.getById(1L);
    }

    @Test
    public void testInsert() {
        boolean saveSuccess = flowService.save(
                new Flow().setName("test流程")
                        .setImage("imageUrl")
                        .setMemberCount(3)
                        .setNodeCount(5)
        );
    }

    @Test
    public void testReadWriteSeparate() {
        Flow flow = flowService.getById(1L);
        flow.setName("测试读写分离");
        boolean success = flowService.updateById(flow);
    }

    @Test
    @Deprecated
    public void testUpdateMaster() {
        log.info("测试主从");
        log.info("更新master");
        Flow flow = flowService.getById(1L);
        flow.setName("主库名变更了哦");
        boolean success = flowService.updateById(flow);
        log.info("master flow name: {}", dynamicProjectServiceFromMybatisPlus.getByIdFromMaster(1L).getName());
        Flow flowSlave1 = dynamicProjectServiceFromMybatisPlus.getByIdFromSlave1(1L);
        log.info("slave1 flow name: {}", flowSlave1.getName());
        Flow flowSlave2 = dynamicProjectServiceFromMybatisPlus.getByIdFromSlave1(1L);
        log.info("slave2 flow name: {}", flowSlave2.getName());
    }

    @Test
    @Deprecated
    public void testUpdateSlave() {
        log.info("测试主从");
        log.info("更新slave1");
        boolean success = dynamicProjectServiceFromMybatisPlus.updateSlave1FlowName("slave1");
        log.info("master flow name: {}", dynamicProjectServiceFromMybatisPlus.getByIdFromMaster(1L).getName());
        Flow flowSlave1 = dynamicProjectServiceFromMybatisPlus.getByIdFromSlave1(1L);
        log.info("slave1 flow name: {}", flowSlave1.getName());
        Flow flowSlave2 = dynamicProjectServiceFromMybatisPlus.getByIdFromSlave1(1L);
        log.info("slave2 flow name: {}", flowSlave2.getName());
    }
} 
