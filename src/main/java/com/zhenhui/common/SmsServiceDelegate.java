package com.zhenhui.common;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmsServiceDelegate {

    private static final String API_URI = "https://v.juhe.cn/sms/send";

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.setVisibility(OBJECT_MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }

    SmsServiceDelegate(SmsServiceProperties properties) {
        this.properties = properties;
        this.httpClient = new OkHttpClient();
    }

    private SmsServiceProperties properties;

    private OkHttpClient httpClient;

    SmsSendResult send(String phone, long templateId, Map<String, String> params) throws IOException {

        final List<String> encodedParams = new ArrayList<>();
        params.forEach((k, v) -> {
            try {
                encodedParams.add(URLEncoder.encode(String.format("#%s#=%s", k, v), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        Request request = new Request.Builder()
                .url(API_URI)
                .post(new FormBody.Builder()
                        .add("key", properties.getAppKey())
                        .add("mobile", phone)
                        .add("tpl_id", String.valueOf(templateId))
                        .add("tpl_value", String.join(",", encodedParams))
                        .build())
                .build();

        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            try {
                return OBJECT_MAPPER.readValue(response.body().string(), new TypeReference<SmsSendResult>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}



