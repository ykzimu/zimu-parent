package com.zimu.domain.info;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MenuInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parentId;

	private String parentIds;

	private String menuType;

	private Integer menuLevel;

	private String menuName;

	private String menuDesc;

	private Integer menuSort;

	private String menuHref;

	private String menuTarget;

	private String menuIcon;

	private Integer isShow;

	private String remarks;

	private List<MenuInfo> childList;

}
