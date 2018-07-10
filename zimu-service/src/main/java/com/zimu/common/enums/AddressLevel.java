package com.zimu.common.enums;

public enum AddressLevel {

    /**
     * 省
     */
    PROVINCE(1),

    /**
     * 市
     */
    CITY(2),

    /**
     * 区县
     */
    AREA(3);

    private int code;

    private AddressLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
