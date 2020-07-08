package com.mrray.ray.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.mrray.ray.common.constant.GlobalConstants;
import com.mrray.ray.common.exception.IExceptionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 通用响应对象
 *
 * @author lyc
 */
@Getter
@Setter
@Accessors(chain = true)
public class GlobalRet<T> implements Serializable {

    private Integer code = GlobalConstants.SUCCESS;

    private String msg = GlobalConstants.SUCCESS_DESC;

    private T data;

    private ExRet error = null;

    private GlobalRet() {
    }

    /**
     * 构造函数
     *
     * @param data 结果集(泛型)
     */
    private GlobalRet(T data) {
        this.data = data;
    }


    public GlobalRet(Throwable e) {
        msg = e.getMessage();
        code = GlobalConstants.FAIL;
    }

    private GlobalRet(IExceptionCode code) {
        if (code != null) {
            this.code = code.getStatus();
            msg = code.getMessage();
        }
    }

    /**
     * 成功时候的调用
     */
    public static <T> GlobalRet<T> success() {
        return new GlobalRet<>();
    }

    /**
     * 成功时候的调用（带数据）
     */
    public static <T> GlobalRet<T> success(T data) {
        return new GlobalRet<>(data);
    }

    /**
     * 成功时候的调用（带数据）
     */
    public static <T> GlobalRet<T> success(int code, String msg, T data) {
        return success(data).setCode(code).setMsg(msg);
    }

    /**
     * 成功时候的调用（标记、带数据）
     */
    public static <T> GlobalRet<T> success(boolean flag, T data) {
        if (flag) {
            return new GlobalRet<>(data);
        } else {
            GlobalRet<T> ret = new GlobalRet<>();
            ret.setCode(GlobalConstants.FAIL);
            ret.setMsg(GlobalConstants.FAIL_DESC);
            return ret;
        }
    }

    /**
     * 成功时候的调用
     */
    public static <T> GlobalRet<T> failure() {
        GlobalRet<T> ret = new GlobalRet<>();
        ret.setCode(GlobalConstants.FAIL);
        ret.setMsg(GlobalConstants.FAIL_DESC);
        return ret;
    }

    /**
     * 失败时候的调用
     */
    public static <T> GlobalRet<T> failure(IExceptionCode iExceptionEnum) {
        return new GlobalRet<>(iExceptionEnum);
    }

    /**
     * 是否成功
     *
     * @return true/false
     */
    @JSONField(serialize = false)
    private boolean isSuccess() {
        return GlobalConstants.SUCCESS == code;
    }

    /**
     * 是否失败
     *
     * @return true/false
     */
    @JSONField(serialize = false)
    public boolean isFail() {
        return !isSuccess();
    }

    /**
     * 提取服务调用返回结果，若返回失败，则抛出业务指定的异常
     *
     * @param s
     * @param <X>
     * @return
     * @throws X
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> s) throws X {
        if (isSuccess()) {
            return getData();
        } else {
            throw s.get();
        }
    }

}
