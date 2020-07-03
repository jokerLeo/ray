package com.mrray.ray.iflow.rpc.service.annotation;

import com.mrray.ray.iflow.core.enums.OrganizationType;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 评估节点注解，此注解标记的AbstractAssessMethod会加入节点容器进行管理
 *
 * @author lyc
 * @create 2019-12-12 13:53
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface Assessor {
    /**
     * 服务名称，唯一标记评估节点,默认为类名
     */
    @AliasFor(annotation = Service.class)
    String value() default "";

    /**
     * 节点名称
     */
    String nodeName();

    /**
     * 图标路径
     */
    String iconUrl() default "";

    /**
     * 组织类型，即平台角色
     */
    OrganizationType organization();

    /**
     * 用户权限配置，多个权限用英文逗号分隔
     *
     * @return
     */
    String permissions() default "";
} 
