package com.zimu.common.exception;

public class ValidationException extends BasicException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String code, Object... variables) {
        super(code, variables);
    }

}
