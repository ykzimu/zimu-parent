package com.zimu.domain.info;

import org.springframework.http.HttpStatus;

public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(HttpStatus.OK),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(1001, "System Error"),

    /**
     * 授权错误
     */
    AUTHENTICATION_ERROR(1002, "Authentication Error"),

    /**
     * 参数校验错误
     */
    VALIDATION_ERROR(1003, "Validation Error"),

    /**
     * 业务错误
     */
    BUSINESS_ERROR(1004, "Business Error");

    private int code;
    private String reasonPhrase;

    ResultCode(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    ResultCode(HttpStatus httpStatus) {
        this(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public int getCode() {
        return code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
