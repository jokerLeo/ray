package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Chain;
import com.mrray.ray.iflow.dao.base.mapper.ChainMapper;
import com.mrray.ray.iflow.rpc.api.base.IChainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

/**
 *  服务实现类
 *
 * @author lyc
 */
@Service(interfaceClass = IChainService.class)
@Component
public class ChainServiceImpl extends ServiceImpl<ChainMapper, Chain> implements IChainService {

}
