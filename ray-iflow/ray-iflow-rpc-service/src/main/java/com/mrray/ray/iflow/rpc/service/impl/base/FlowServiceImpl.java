package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Flow;
import com.mrray.ray.iflow.dao.base.mapper.FlowMapper;
import com.mrray.ray.iflow.rpc.api.base.IFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

/**
 *  服务实现类
 *
 * @author lyc
 */
@Service(interfaceClass = IFlowService.class)
@Component
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements IFlowService {

}
