package com.chen.core.config;

import com.chen.constant.SpringBeanNameConstant;
import com.chen.core.entity.to.CustomSecurityAttributeTO;
import com.chen.core.entity.to.LoginsTO;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义yml的读取配置
 *
 * @author ： CatalpaFlat
 * @date ：Create in 21:09 2017/12/20
 */
@Configuration(value = SpringBeanNameConstant.DEFAULT_CUSTOM_YML_CONFIG_SERVICE_BN)
@ConfigurationProperties(prefix = "catalpaFlat")
public class CustomYmlConfig {

    private static final Logger logger = LoggerFactory.getLogger(CustomYmlConfig.class.getName());
    @Getter
    @Setter
    private LoginsTO logins = new LoginsTO();
    @Getter
    @Setter
    private CustomSecurityAttributeTO security = new CustomSecurityAttributeTO();


    public CustomYmlConfig() {
        logger.info("Loading CustomYmlConfig...");
    }
}
