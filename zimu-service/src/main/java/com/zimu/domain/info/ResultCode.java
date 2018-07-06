package com.zimu.domain.info;

public enum ResultCode {
	SUCCESS("0000", "success"), //
	SYSTEM_ERROR("1001", "system error"), //
	AUTH_ERROR("1002", "Verification failure"), //
	ARGUMENT_ERROR("1003", "arguments error"), //
	BUSINESS_ERROR("1004", "business error");//

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
