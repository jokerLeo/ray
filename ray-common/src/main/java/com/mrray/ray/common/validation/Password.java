package com.mrray.ray.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Description: 密码验证注解
 *
 * @author 然诺
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidator.class})
public @interface Password {
    String message() default "密码必须为6-16位且包含大、小写字母、数字、特殊符号中的两种及以上";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
