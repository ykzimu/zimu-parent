package com.zimu.component;


import com.zimu.domain.info.SelectInfo;
import com.zimu.entity.RoleEntity;

import java.util.List;

public interface CommonComponent {

    RoleEntity getRoleByRoleCode(String roleCode);

    List<SelectInfo> getPageSizeList();


}
