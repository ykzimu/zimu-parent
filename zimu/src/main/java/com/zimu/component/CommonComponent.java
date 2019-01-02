package com.zimu.component;


import java.util.List;

import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.info.SelectInfo;

public interface CommonComponent {

    RoleEntity getRoleByRoleCode(String roleCode);

    List<SelectInfo> getPageSizeList();


}
