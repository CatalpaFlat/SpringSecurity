package com.chen.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author ： CatalpaFlat
 * @date ：Create in 9:36 2017/12/21
 */
public class CustomException extends RuntimeException {
    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private Integer code;
    public CustomException(Integer code,String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
