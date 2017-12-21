package com.chen.core.http.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应体疯转类
 *
 * @author ： CatalpaFlat
 * @date ：Create in 14:44 2017/12/20
 */
public class Response {
    @Getter
    @Setter
    private Integer Status;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private Object result;
    @Getter
    @Setter
    private Object error;
}
