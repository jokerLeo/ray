package com.mrray.ray.common.validation;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 实体校验帮助类
 *
 * @author lyc
 **/
public class ValidateUtils {
    private ValidateUtils() {
    }

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    /**
     * 校验实体，返回实体所有属性的校验结果
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateEntity(T obj) {
        Set<ConstraintViolation<T>> validateSet = validator.validate(obj, Default.class);
        return buildValidationResult(validateSet);
    }

    /**
     * 校验实体
     *
     * @param obj
     * @param <T>
     */
    public static <T> void validate(T obj) {
        if (null == obj) {
            throw new IllegalArgumentException("请填写必要的数据");
        }
        Set<ConstraintViolation<T>> validateSet = validator.validate(obj, Default.class);
        ValidationResult result = buildValidationResult(validateSet);
        if (result.isHasErrors()) {
            throw new IllegalArgumentException(result.getErrors());
        }
    }

    /**
     * 校验指定实体的指定属性是否存在异常
     *
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> validateSet = validator.validateProperty(obj, propertyName, Default.class);
        return buildValidationResult(validateSet);
    }

    /**
     * 将异常结果封装返回
     *
     * @param validateSet
     * @param <T>
     * @return
     */
    private static <T> ValidationResult buildValidationResult(Set<ConstraintViolation<T>> validateSet) {
        ValidationResult validationResult = new ValidationResult();
        if (CollectionUtils.isNotEmpty(validateSet)) {
            validationResult.setHasErrors(true);
            Map<String, String> errorMsgMap = new HashMap<>(20);
            for (ConstraintViolation<T> constraintViolation : validateSet) {
                errorMsgMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
            validationResult.setErrorMsg(errorMsgMap);
        }
        return validationResult;
    }
} 
