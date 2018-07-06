package com.zimu.common.enums;

public enum RoleEnum {

	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "超级管理员"), //
	ROLE_ADMIN("ROLE_ADMIN", "管理员"), //
	ROLE_DBA("ROLE_DBA", "数据库管理员"), //
	ROLE_API("ROLE_API", "API接口权限"), //
	ROLE_USER("ROLE_USER", "普通用户");

	private String roleCode;

	private String roleName;

	private RoleEnum(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

}
