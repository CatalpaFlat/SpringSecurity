package com.chen.core.security.config;

import com.chen.constant.SpringBeanNameConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.Filter;

/**
 * 验证码Security配置
 *
 * @author ： CatalpaFlat
 * @date ：Create in 14:34 2017/12/21
 */
@Configuration(SpringBeanNameConstant.DEFAULT_VALIDATE_CODE_SECURITY_CONFIG_BN)
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeSecurityConfig.class.getName());

    public ValidateCodeSecurityConfig() {
        logger.info("ValidateCodeSecurityConfig loading.....");
    }

    @Autowired
    private Filter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
