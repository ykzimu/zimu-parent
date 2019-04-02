package com.zimu.component;

import com.zimu.domain.entity.RoleEntity;

import java.util.List;
import java.util.Map;

public interface RoleComponent {

    Map<String, String[]> getRoleMenus();

    List<RoleEntity> getRolesByUserId(Long userId);
}
