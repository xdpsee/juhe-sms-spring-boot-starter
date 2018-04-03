package com.zhenhui.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class SmsService implements InitializingBean {

    @Autowired
    private SmsServiceProperties properties;

    private SmsServiceDelegate delegate;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.delegate = new SmsServiceDelegate(properties);
    }

    /**
     *
     * @param phone
     * @param templateId
     * @param params
     * @return null or SmsSendResult
     * @throws IOException
     */
    public SmsSendResult send(String phone, long templateId, Map<String, String> params) throws IOException {
        return delegate.send(phone, templateId, params);
    }

}
