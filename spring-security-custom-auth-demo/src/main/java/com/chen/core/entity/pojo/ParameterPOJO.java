package com.chen.core.entity.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 手机登录验证码提交参数
 *
 * @author ： CatalpaFlat
 * @date ：Create in 9:44 2017/12/21
 */
public class ParameterPOJO {
    @Getter
    @Setter
    private String mobile;
    @Getter
    @Setter
    private String code;
}
