package com.zimu.view.dashboard;

import com.zimu.common.view.annotation.ViewName;
import com.zimu.common.view.BaseView;
import com.zimu.common.view.ViewNames;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName(ViewNames.DASHBOARD_INDEX)
public class DashboardIndexView implements BaseView {

}
