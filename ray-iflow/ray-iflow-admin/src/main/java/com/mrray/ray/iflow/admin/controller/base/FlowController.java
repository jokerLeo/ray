package com.mrray.ray.iflow.admin.controller.base;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mrray.ray.common.result.GlobalRet;
import com.mrray.ray.iflow.dao.base.model.Flow;
import com.mrray.ray.iflow.rpc.api.base.IFlowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author lyc
 */
@RestController
@RequestMapping("/flow")
public class FlowController {
    @Reference
    private IFlowService flowService;

    @GetMapping("/test")
    public GlobalRet<Flow> test() {
        return GlobalRet.success(flowService.getById(1L));
    }

}
