package com.zimu.view.menu;

import com.zimu.common.view.BaseView;
import com.zimu.common.view.annotation.ViewName;
import com.zimu.common.view.ViewNames;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName(ViewNames.MENU_LIST)
public class MenuListView implements BaseView {

}
