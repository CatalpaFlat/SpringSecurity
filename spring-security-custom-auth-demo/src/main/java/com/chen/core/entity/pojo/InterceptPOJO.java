package com.chen.core.entity.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

/**
 * security 拦截的请求方法，url所需的权限
 *
 * @author ： CatalpaFlat
 * @date ：Create in 21:13 2017/12/20
 */
public class InterceptPOJO {

    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private HttpMethod httpMethod;
}
