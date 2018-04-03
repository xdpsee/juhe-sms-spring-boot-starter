package com.zhenhui.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("sms.juhe")
public class SmsServiceProperties {

    private boolean enabled;

    private String appKey = "";

}
