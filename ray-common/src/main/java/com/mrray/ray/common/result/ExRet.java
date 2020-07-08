package com.mrray.ray.common.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常信息对象
 *
 * @author lyc
 */
@Getter
@Setter
class ExRet implements Serializable {

    private Date date;

    private String type;

    private String message;

    private String stackTrace;

}
