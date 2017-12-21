package com.chen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * start
 *
 * @author ： CatalpaFlat
 * @date ：Create in 20:52 2017/12/20
 */
@SpringBootApplication
@PropertySource("classpath:properties/custom.properties")
public class CustomAuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(CustomAuthApplication.class, args);
        logger.info("CustomAuthApplication start success...");
    }
}
