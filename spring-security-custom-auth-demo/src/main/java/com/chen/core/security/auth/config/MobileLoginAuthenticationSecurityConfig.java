package com.chen.core.security.auth.config;

import com.chen.constant.SpringBeanNameConstant;
import com.chen.core.config.CustomYmlConfig;
import com.chen.core.entity.pojo.MobilePOJO;
import com.chen.core.security.auth.filter.MobileLoginAuthenticationFilter;
import com.chen.core.security.auth.provider.MobileLoginAuthenticationProvider;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 手机短信验证码认证配置
 * 1.认证过程
 *
 *  生成未认证成功的AuthenticationToken           生成认证成功的AuthenticationToken
 *        ↑                                                 ↑
 * AuthenticationFilter  ->  AuthenticationManager -> AuthenticationProvider
 *                                                           ↓
 *                                                      UserDetails（认证）
 *
 * 2. 将AuthenticationFilter加入到security过滤链（资源服务器中配置），如：
 *  http.addFilterBefore(AuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
 *
 * @author ： CatalpaFlat
 * @date ：Create in 22:22 2017/12/20
 */
@Configuration(SpringBeanNameConstant.DEFAULT_CUSTOM_MOBILE_LOGIN_AUTHENTICATION_SECURITY_CONFIG_BN)
public class MobileLoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private static final Logger logger = LoggerFactory.getLogger(MobileLoginAuthenticationSecurityConfig.class.getName());
    @Value("${login.mobile.url}")
    private String defaultMobileLoginUrl;
    @Value("${login.mobile.parameter}")
    private String defaultMobileLoginParameter;
    @Value("${login.mobile.httpMethod}")
    private String defaultMobileLoginHttpMethod;

    @Autowired
    private CustomYmlConfig customYmlConfig;
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;


    public MobileLoginAuthenticationSecurityConfig() {
        logger.info("MobileLoginAuthenticationSecurityConfig loading ...");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MobilePOJO mobile = customYmlConfig.getLogins().getMobile();
        String url = mobile.getUrl();
        String parameter = mobile.getParameter().getMobile();
        String httpMethod = mobile.getHttpMethod();

        MobileLoginAuthenticationFilter mobileLoginAuthenticationFilter = new MobileLoginAuthenticationFilter(StringUtils.isBlank(url) ? defaultMobileLoginUrl : url,
                StringUtils.isBlank(parameter) ? defaultMobileLoginUrl : parameter, StringUtils.isBlank(httpMethod) ? defaultMobileLoginHttpMethod : httpMethod);

        mobileLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mobileLoginAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        mobileLoginAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        MobileLoginAuthenticationProvider mobileLoginAuthenticationProvider = new MobileLoginAuthenticationProvider();
        mobileLoginAuthenticationProvider.setCustomUserDetailsService(customUserDetailsService);

        http.authenticationProvider(mobileLoginAuthenticationProvider)
                .addFilterAfter(mobileLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
