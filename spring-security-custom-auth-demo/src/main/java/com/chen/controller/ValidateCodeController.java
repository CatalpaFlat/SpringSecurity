package com.chen.controller;

import com.chen.constant.SpringUrlConstant;
import com.chen.constant.SystemConstant;
import com.chen.core.entity.po.SmsCodePO;
import com.chen.core.exception.CustomException;
import com.chen.core.http.utils.ResponseUtil;
import com.chen.core.http.vo.Response;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 接口
 *
 * @author ： CatalpaFlat
 * @date ：Create in 22:48 2017/12/20
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeController.class.getName());

    @GetMapping(SpringUrlConstant.DEFAULT_LOGIN_MOBILE_SENDER_CODE_URL + "/{mobile}")
    public Response sendCode(HttpServletRequest request, @PathVariable String mobile) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char c = (char) (new Random().nextInt(10) + '0');
            sb.append(String.valueOf(c));
        }
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Not deviceId in the head of the request");
        }
        int expireTime = 180;
        SmsCodePO smsCode = new SmsCodePO(sb.toString(), expireTime);

        redisTemplate.opsForValue().set(SystemConstant.DEFAULT_MOBILE_KEY_PIX + deviceId, smsCode, expireTime, TimeUnit.SECONDS);
        logger.info("向手机：" + mobile + ",发送验证码：[" + sb + "]");
        return ResponseUtil.success("send code success");
    }

}
