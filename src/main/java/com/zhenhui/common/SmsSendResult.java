package com.zhenhui.common;

import lombok.Data;

@Data
public class SmsSendResult {

    private int error_code;

    private String reason;

    private Result result;

    @Data
    public static class Result {

        private String sid;

        private int count;

        private int fee;

    }

}

