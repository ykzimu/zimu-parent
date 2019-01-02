package com.zimu.domain.info;

public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS("0000", "success"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR("1001", "system error"),

    /**
     * 授权错误
     */
    AUTH_ERROR("1002", "Verification failure"),

    /**
     * 参数错误
     */
    ARGUMENT_ERROR("1003", "arguments error"),

    /**
     * 业务错误
     */
    BUSINESS_ERROR("1004", "business error");

    private String resultcode;
    private String msg;

    ResultCode(String resultcode, String msg) {
        this.resultcode = resultcode;
        this.msg = msg;
    }

    public String getResultcode() {
        return resultcode;
    }

    public String getMsg() {
        return msg;
    }
}
