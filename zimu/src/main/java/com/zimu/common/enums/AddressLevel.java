package com.zimu.common.enums;

public enum AddressLevel {

    PROVINCE(1), CITY(2), AREA(3);

    private int code;

    private AddressLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
