package com.zimu.view.admin;

import com.zimu.annotation.ViewName;
import com.zimu.view.BaseView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName("/views/dashboard/index")
public class DashboardIndexView implements BaseView {

}
