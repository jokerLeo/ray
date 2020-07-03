package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Operation;
import com.mrray.ray.iflow.rpc.api.base.IOperationService;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分布式事务
 *
 * @author lyc
 **/
@Service
public class TransactionalService {
    @Autowired
    private IOperationService operationService;

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.LOCAL)
    public boolean testTransactionLocal() {
        boolean insert1Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入1").setAssessStatus(0));
        boolean insert2Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入2").setAssessStatus(1));
        return insert1Success && insert2Success;
    }

    @Transactional(rollbackFor = Exception.class)
    //需要引入seata
    //@ShardingTransactionType(TransactionType.BASE)
    public boolean testTransactionBase() {
        boolean insert1Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入1").setAssessStatus(0));
        boolean insert2Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入2").setAssessStatus(1));
        return insert1Success && insert2Success;
    }

    @Transactional(rollbackFor = Exception.class)
    //需要引入Atomikos
    //@ShardingTransactionType(TransactionType.XA)
    public boolean testTransactionXA() {
        boolean insert1Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入1").setAssessStatus(0));
        boolean insert2Success = operationService.save(new Operation().setOperationDescription("测试分布式事务插入2").setAssessStatus(1));
        return insert1Success && insert2Success;
    }
} 
