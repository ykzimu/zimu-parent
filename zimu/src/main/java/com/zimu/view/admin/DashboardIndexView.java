package com.zimu.view.admin;

import com.zimu.view.BaseView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DashboardIndexView extends BaseView {

    /**
     * viewName
     */
    private static final String VIEWNAME = "/views/dashboard/index";

    @Override
    protected String viewName() {
        return VIEWNAME;
    }
}
