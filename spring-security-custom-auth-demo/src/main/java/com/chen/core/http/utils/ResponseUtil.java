package com.chen.core.http.utils;


import com.chen.core.http.vo.Response;

/**
 * http 响应体工具
 *
 * @author ： CatalpaFlat
 * @date ：Create in 14:49 2017/12/20
 */
public class ResponseUtil {

    /**
     * http 响应成功
     */
    public static Response success(String msg, Object result) {
        Response response = getResponse(msg);
        response.setStatus(200);
        response.setResult(result);
        return response;
    }

    /**
     * http 响应成功
     */
    public static Response success(String msg) {
        Response response = getResponse(msg);
        response.setStatus(200);
        return response;
    }

    /**
     * http 响应失败
     */
    public static Response error(Integer status, String msg, Object error) {
        Response response = getResponse(msg);
        response.setStatus(status);
        response.setError(error);
        return response;
    }

    /**
     * http 响应失败
     */
    public static Response error(Integer status, String msg) {
        Response response = getResponse(msg);
        response.setStatus(status);
        return response;
    }

    /**
     * 获取Response
     */
    private static Response getResponse(String msg) {
        Response response = new Response();
        response.setMsg(msg);
        return response;
    }
}
