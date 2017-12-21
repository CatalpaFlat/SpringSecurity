package com.chen.core.entity.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * security 匹配器属性
 * @author ： CatalpaFlat
 * @date ：Create in 21:12 2017/12/20
 */
public class AntMatchersPOJO {

    @Getter
    @Setter
    private String[] permitAll;

    @Getter
    @Setter
    private List<InterceptPOJO> intercept;
}
