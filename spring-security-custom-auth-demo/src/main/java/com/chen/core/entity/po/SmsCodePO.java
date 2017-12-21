package com.chen.core.entity.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信验证码
 *
 * @author ： CatalpaFlat
 * @date ：Create in 16:58 2017/12/21
 */
public class SmsCodePO implements Serializable {

    @Getter
    @Setter
    protected String code;
    @Getter
    @Setter
    protected LocalDateTime expireTime;

    // ~ Constructor
    // ========================================================================================================

    public SmsCodePO(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public SmsCodePO(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    // ~ Methods
    // ========================================================================================================

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
