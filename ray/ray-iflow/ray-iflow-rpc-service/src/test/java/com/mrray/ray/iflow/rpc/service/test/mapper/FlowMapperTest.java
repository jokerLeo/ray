package com.mrray.ray.iflow.rpc.service.test.mapper;

import com.mrray.ray.iflow.dao.base.model.Flow;
import com.mrray.ray.iflow.rpc.api.base.IFlowService;
import com.mrray.ray.iflow.rpc.service.IFlowRpcServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FlowMapperTest
 *
 * @author lyc
 **/
@SpringBootTest(classes = IFlowRpcServiceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class FlowMapperTest {
    @Autowired
    private IFlowService flowService;

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

    /**
     * 测试读写分离
     */
    @Test
    public void testReadWrite() {
        Flow flow = flowService.getById(1L);
        boolean isSuccess = flowService.save(new Flow().setName("测试读写分离"));
    }
} 
