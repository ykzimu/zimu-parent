package com.zimu.view.admin;

import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.info.SelectInfo;
import com.zimu.view.BaseView;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuAddView extends BaseView {

    /**
     * viewName
     */
    private static final String VIEWNAME = "/views/menu/add";

    @Override
    protected String viewName() {
        return VIEWNAME;
    }

    private MenuEntity menuInfo;

    private List<SelectInfo> menuHrefs;
}
