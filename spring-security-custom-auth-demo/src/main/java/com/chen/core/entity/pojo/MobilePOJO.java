package com.chen.core.entity.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 手机登录认证实体
 *
 * @author ： CatalpaFlat
 * @date ：Create in 22:31 2017/12/20
 */
public class MobilePOJO {
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private ParameterPOJO parameter;
    @Getter
    @Setter
    private String httpMethod;
}
