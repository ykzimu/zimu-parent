package com.zimu.view.admin;

import com.zimu.view.BaseView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
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
