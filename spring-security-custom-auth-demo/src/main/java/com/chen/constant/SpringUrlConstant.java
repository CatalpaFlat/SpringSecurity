package com.chen.constant;

/**
 * 接口url常量汇总
 *
 * @author ： CatalpaFlat
 * @date ：Create in 22:48 2017/12/20
 */
public final class SpringUrlConstant {
    private SpringUrlConstant() {
    }
    ;
    /**
     * 默认登录页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/login.html";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/login";

    public static final String DEFAULT_LOGIN_MOBILE_SENDER_CODE_URL = "/code/sms";
}
