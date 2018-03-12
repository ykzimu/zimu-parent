package com.zimu.common.exception;

public class BusinessException extends RuntimeException {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6660798624181643551L;

	private String _exceptionCode = null;
	private Object[] _variables;

	public BusinessException() {
		_exceptionCode = ExceptionConstants.DEFAULT;
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String exceptionCode, String msg) {
		super(msg);
		_exceptionCode = exceptionCode;
	}

	public BusinessException(String msg, Throwable e) {
		super(msg, e);
	}

	/**
	 * 
	 * @param exceptionCode
	 * @param e
	 * @param args
	 */
	public BusinessException(String exceptionCode, Throwable e,
			Object[] variables) {
		super(e);
		_exceptionCode = exceptionCode;
		_variables = variables;
	}

	public BusinessException(Throwable e) {
		super(e);
		_exceptionCode = ExceptionConstants.DEFAULT;
	}

	public String getExceptionCode() {
		return _exceptionCode;
	}

	public Object[] getVariables() {
		return _variables;
	}

	public void setExceptionCode(String _exceptionCode) {
		this._exceptionCode = _exceptionCode;
	}

	public void setVariables(Object[] _variables) {
		this._variables = _variables;
	}

	public String toString() {
		String msg = null;

		msg = super.getMessage();
		if (msg == null)
			msg = _exceptionCode;

		return msg;
	}

}
