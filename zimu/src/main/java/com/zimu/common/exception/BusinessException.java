package com.zimu.common.exception;

public class BusinessException extends BasicException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, Object... variables) {
        super(code, variables);
    }

}
