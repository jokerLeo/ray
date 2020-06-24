package com.mrray.ray.iflow.core.exception;


/**
 * @author lyc
 * @version 1.0
 * @description 流程异常
 * @createDate 2020/2/24 14:10
 **/
public class ChainException extends RuntimeException {

    public ChainException() {
        super();
    }

    public ChainException(String message) {
        super(message);
    }

    public ChainException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChainException(Throwable cause) {
        super(cause);
    }

}
