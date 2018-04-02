package com.zhenhui.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("sms.juhe")
public class SmsServiceProperties {

    private String appKey;

}
