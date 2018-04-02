package com.zhenhui.common;

import java.io.IOException;
import java.util.Map;

public interface SmsService {

    /**
     * 发送短信息
     * @param phone 手机号码
     * @param templateId 短信模板ID
     * @param params 模板参数
     * @return
     */
    SmsSendResult send(String phone, long templateId, Map<String, String> params) throws IOException;

}
