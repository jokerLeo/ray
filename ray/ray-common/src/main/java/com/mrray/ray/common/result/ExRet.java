package com.mrray.ray.common.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：异常信息对象
 *
 * @author 然诺
 * @date 2019/8/28
 */
@Getter
@Setter
public class ExRet implements Serializable {

    private Date date;

    private String type;

    private String message;

    private String stackTrace;

}
