package com.zimu.domain.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class JsonView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Msg msg;

	private Object data;

	public JsonView() {
		this(ResultCode.SUCCESS);
	}

	public JsonView(ResultCode resultCode) {
		msg = new Msg();
		msg.setMsg(resultCode);
	}

}
