package com.mrray.ray.iflow.admin.controller.base;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mrray.ray.common.result.GlobalRet;
import com.mrray.ray.iflow.dao.base.model.Operation;
import com.mrray.ray.iflow.rpc.api.base.IOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作记录
 *
 * @author lyc
 */
@Api(tags = "操作记录")
@RestController
@RequestMapping("/operation")
public class OperationController {
    @Reference
    private IOperationService operationService;

    @GetMapping("/testSelect")
    @ApiOperation("测试获取某一操作记录")
    public GlobalRet<Operation> testSelect() {
        return GlobalRet.success(operationService.getById(1278886232792793090L));
    }

    @GetMapping("/testInsert")
    @ApiOperation("测试插入操作")
    public GlobalRet<Boolean> testInsert() {
        return GlobalRet.success(operationService.save(new Operation()
                .setOperationDescription("测试监控插入操作")
                .setAssessStatus(0)));
    }

    @GetMapping("/testUpdate")
    @ApiOperation("测试更新操作")
    public GlobalRet<Boolean> testUpdate() {
        Operation operation = operationService.getById(1278886232792793090L);
        //分库分表字段不能更新，要设置为null，否则异常
        operation.setOperationDescription("测试监控更新操作").setAssessStatus(null);
        return GlobalRet.success(operationService.updateById(operation));
    }

}
