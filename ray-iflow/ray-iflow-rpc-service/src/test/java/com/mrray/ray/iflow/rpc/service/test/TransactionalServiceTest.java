package com.mrray.ray.iflow.rpc.service.test;

import com.mrray.ray.iflow.rpc.service.IFlowRpcServiceApplication;
import com.mrray.ray.iflow.rpc.service.impl.base.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试分布式事务
 *
 * @author lyc
 **/
@SpringBootTest(classes = IFlowRpcServiceApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TransactionalServiceTest {
    @Autowired
    private TransactionalService transactionalService;

    @Test
    public void testInsertTwoRecords() {
        log.debug(String.valueOf(transactionalService.testTransactionLocal()));
        //log.debug(String.valueOf(transactionalService.testTransactionBase()));
        //log.debug(String.valueOf(transactionalService.testTransactionXA()));
    }
} 
