package com.zimu.domain.info;

import com.zimu.domain.entity.MenuEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuInfo extends MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MenuInfo> childList;

    private List<MenuInfo> children;

}
