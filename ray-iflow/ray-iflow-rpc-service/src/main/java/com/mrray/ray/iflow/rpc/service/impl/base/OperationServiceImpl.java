package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Operation;
import com.mrray.ray.iflow.dao.base.mapper.OperationMapper;
import com.mrray.ray.iflow.rpc.api.base.IOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

/**
 *  服务实现类
 *
 * @author lyc
 */
@Service(interfaceClass = IOperationService.class)
@Component
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements IOperationService {

}
