package com.zimu.view.admin;

import com.zimu.common.view.BaseView;
import com.zimu.common.view.ViewNames;
import com.zimu.common.view.annotation.ViewName;
import com.zimu.domain.info.SelectInfo;
import com.zimu.entity.MenuEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName(ViewNames.MENU_ADD)
public class MenuAddView implements BaseView {

    private MenuEntity menuInfo;

    private List<SelectInfo> menuHrefs;
}
