package com.zimu.common.exception;

public class AuthenticationException extends BasicException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String code, Object... variables) {
        super(code, variables);
    }

}
