package com.zimu.domain.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;

	private String name;
}
