package com.zimu.view.admin;

import com.zimu.view.BaseView;
import lombok.Builder;

@Builder
public class MenuListView extends BaseView {

    /**
     * viewName
     */
    private static final String VIEWNAME = "/views/menu/list";

    @Override
    protected String viewName() {
        return VIEWNAME;
    }
}
