package com.chen.core.security.config;

import com.chen.constant.SpringBeanNameConstant;
import com.chen.constant.SpringUrlConstant;
import com.chen.core.config.CustomYmlConfig;
import com.chen.core.entity.pojo.AntMatchersPOJO;
import com.chen.core.entity.pojo.InterceptPOJO;
import com.chen.core.entity.to.CustomSecurityAttributeTO;
import com.chen.core.security.auth.config.MobileLoginAuthenticationSecurityConfig;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.List;

/**
 * security 配置
 *
 * @author ： CatalpaFlat
 * @date ：Create in 21:07 2017/12/20
 */
@Configuration
@AutoConfigureAfter(name = SpringBeanNameConstant.DEFAULT_CUSTOM_YML_CONFIG_SERVICE_BN)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class.getName());


    @Autowired
    private CustomYmlConfig customYmlConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private MobileLoginAuthenticationSecurityConfig mobileLoginAuthenticationSecurityConfig;

    public SecurityConfig() {
        logger.info("CustomSecurityConfig loading ...");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomSecurityAttributeTO security = customYmlConfig.getSecurity();
        AntMatchersPOJO antMatchers = security.getAntMatchers();
        String[] permitAll = antMatchers.getPermitAll();
        List<InterceptPOJO> intercepts = antMatchers.getIntercept();

        //登录
        http.httpBasic().and()
                //认证
                .authorizeRequests()
                .antMatchers(permitAll)
                .permitAll()
                .and()
                //关闭跨站维护
                .csrf().disable()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(mobileLoginAuthenticationSecurityConfig);


        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config = http.authorizeRequests();

        if (CollectionUtils.isNotEmpty(intercepts)) {
            for (InterceptPOJO intercept : intercepts) {
                config.antMatchers(intercept.getHttpMethod(), intercept.getUrl()).
                        hasRole(intercept.getRole());
            }
        }
        config.anyRequest()//任何请求
                .authenticated();//都需要身份认证
    }
}
