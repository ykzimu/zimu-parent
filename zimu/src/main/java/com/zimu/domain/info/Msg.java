package com.zimu.domain.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String codeDesc;

	private String message;

	public Msg() {
		this.setMsg(ResultCode.SUCCESS);
	}

	public Msg(String code) {
		this.setMsg(ResultCode.valueOf(code));
	}

	public Msg(ResultCode resultCode) {
		this.setMsg(resultCode);
	}

	public void setMsg(ResultCode resultCode) {
		this.code = resultCode.getResultcode();
		this.message = resultCode.getMsg();
		this.codeDesc = resultCode.getMsg();
	}

}
