package com.chen.core.security;

import com.chen.constant.SpringBeanNameConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义用户信息
 *
 * @author ： CatalpaFlat
 * @date ：Create in 21:00 2017/12/20
 */
@Component(SpringBeanNameConstant.DEFAULT_CUSTOM_USER_DETAIL_SERVICE_BN)
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class.getName());

    public CustomUserDetailsService() {
        logger.info("CustomUserDetailsService loading success ...");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username:"+username);
        return new User(username, "admin",
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
