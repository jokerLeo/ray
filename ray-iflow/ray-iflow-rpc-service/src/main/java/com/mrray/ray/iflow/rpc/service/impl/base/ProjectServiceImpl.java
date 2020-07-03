package com.mrray.ray.iflow.rpc.service.impl.base;

import com.mrray.ray.iflow.dao.base.model.Project;
import com.mrray.ray.iflow.dao.base.mapper.ProjectMapper;
import com.mrray.ray.iflow.rpc.api.base.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 项目 服务实现类
 *
 * @author lyc
 */
@Service(interfaceClass = IProjectService.class)
@Component
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
