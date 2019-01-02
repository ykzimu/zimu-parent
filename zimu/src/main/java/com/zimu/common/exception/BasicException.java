package com.zimu.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BasicException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Object[] variables;

    public BasicException() {
        super();
    }

    public BasicException(String message) {
        super(message);
    }

    public BasicException(String code, Object... variables) {
        super(code);
        this.variables = variables;
    }

}
