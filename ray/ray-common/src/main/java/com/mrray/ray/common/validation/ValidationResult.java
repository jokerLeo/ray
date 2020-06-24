package com.mrray.ray.common.validation;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

/**
 * 实体校验结果
 *
 * @author lyc
 **/
public class ValidationResult {
    @Getter
    @Setter
    private boolean hasErrors;
    @Setter
    private Map<String, String> errorMsg;

    /**
     * 返回所有错误信息
     *
     * @return
     */
    String getErrors() {
        if (MapUtils.isEmpty(errorMsg)) {
            return StringUtils.EMPTY;
        }
        StringBuilder message = new StringBuilder();
        errorMsg.forEach((key, value) ->
                message.append(MessageFormat.format("{0}:{1} \r\n", key, value))
        );
        return message.toString();
    }

    /**
     * 返回第一条错误信息
     *
     * @return
     */
    public String getError() {
        if (MapUtils.isEmpty(errorMsg)) {
            return StringUtils.EMPTY;
        }
        Optional<Map.Entry<String, String>> firstOptional = errorMsg.entrySet().stream().findFirst();
        if (!firstOptional.isPresent()) {
            return "";
        }
        Map.Entry<String, String> first = firstOptional.get();
        return first.getKey() + ":" + first.getValue();
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "hasErrors=" + hasErrors +
                ", errorMsg=" + errorMsg +
                '}';
    }
} 
