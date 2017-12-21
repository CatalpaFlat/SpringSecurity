package com.chen.core.exception;

import com.chen.core.http.utils.ResponseUtil;
import com.chen.core.http.vo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常
 *
 * @author ： CatalpaFlat
 * @date ：Create in 9:40 2017/12/21
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public Response handlerNotExistException(CustomException e, HttpServletResponse response) {
        response.setStatus(e.getCode());
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }
}
