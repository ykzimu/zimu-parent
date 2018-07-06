package com.zimu.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zimu.component.CommonComponent;
import com.zimu.dao.RoleEntityMapper;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.entity.RoleEntityExample;
import com.zimu.domain.info.SelectInfo;

@Component
public class CommonComponentImpl implements CommonComponent {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    public RoleEntity getRoleByRoleCode(String roleCode) {

        //查询用户权限信息
        RoleEntityExample roleEntityExample = new RoleEntityExample();
        roleEntityExample.or().andRoleCodeEqualTo(roleCode);
        List<RoleEntity> list = roleEntityMapper.selectByExample(roleEntityExample);
        if (list == null || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

	public List<SelectInfo> getPageSizeList() {
		List<SelectInfo> list = new ArrayList<SelectInfo>();

		SelectInfo selectInfo = new SelectInfo();
		selectInfo.setId("10");
		selectInfo.setText("10");
		list.add(selectInfo);

		selectInfo = new SelectInfo();
		selectInfo.setId("20");
		selectInfo.setText("20");
		list.add(selectInfo);

		selectInfo = new SelectInfo();
		selectInfo.setId("50");
		selectInfo.setText("50");
		list.add(selectInfo);

		selectInfo = new SelectInfo();
		selectInfo.setId("100");
		selectInfo.setText("100");
		list.add(selectInfo);
		return list;
	}
}
