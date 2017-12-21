package com.chen.core.security.filter;

import com.chen.constant.SpringBeanNameConstant;
import com.chen.constant.SystemConstant;
import com.chen.core.config.CustomYmlConfig;
import com.chen.core.entity.po.SmsCodePO;
import com.chen.core.exception.CustomException;
import com.chen.core.security.config.ValidateCodeSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * <p>
 * OncePerRequestFilter确保过滤器每次只被调用一次
 *
 * @author ： CatalpaFlat
 * @date ：Create in 9:17 2017/12/21
 */
@Component(SpringBeanNameConstant.DEFAULT_VALIDATE_CODE_FILTER_BN)
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class.getName());

    @Autowired
    private CustomYmlConfig customYmlConfig;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public ValidateCodeFilter() {
        logger.info("Loading ValidateCodeFilter...");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = customYmlConfig.getLogins().getMobile().getUrl();
        if (pathMatcher.match(url, request.getRequestURI())) {
            String deviceId = request.getHeader("deviceId");
            if (StringUtils.isBlank(deviceId)) {
                throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Not deviceId in the head of the request");
            }
            String codeParamName = customYmlConfig.getLogins().getMobile().getParameter().getCode();
            String code = request.getParameter(codeParamName);
            if (StringUtils.isBlank(code)) {
                throw new CustomException(HttpStatus.NOT_ACCEPTABLE.value(), "Not code in the parameters of the request");
            }
            String key = SystemConstant.DEFAULT_MOBILE_KEY_PIX + deviceId;
            SmsCodePO smsCodePo = (SmsCodePO) redisTemplate.opsForValue().get(key);
            if (smsCodePo.isExpried()){
                throw new CustomException(HttpStatus.BAD_REQUEST.value(), "The verification code has expired");
            }
            String smsCode = smsCodePo.getCode();
            if (StringUtils.isBlank(smsCode)) {
                throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Verification code does not exist");
            }
            if (StringUtils.equals(code, smsCode)) {
                redisTemplate.delete(key);
                //let it go
                filterChain.doFilter(request, response);
            } else {
                throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Validation code is incorrect");
            }
        }else {
            //let it go
            filterChain.doFilter(request, response);
        }
    }
}
