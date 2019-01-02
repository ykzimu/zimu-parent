package com.zimu.domain.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class SelectInfo implements Serializable {

	private static final long serialVersionUID = -2907277499907960735L;

	private String id;

	private String text;

}
