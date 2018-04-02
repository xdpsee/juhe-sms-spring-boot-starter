package com.zhenhui.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SmsServiceImpl.class)
@EnableConfigurationProperties(SmsServiceProperties.class)
public class SmsServiceAutoConfiguration {

}
