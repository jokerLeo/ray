package com.mrray.ray.common.excel;

import java.lang.annotation.*;

/**
 * excel导出实体的注解
 *
 * @author lyc
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     *
     * @return
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     *
     * @return
     */
    int col() default 0;
}

