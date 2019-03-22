package com.zimu.view.admin;

import com.zimu.annotation.ViewName;
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
@ViewName("/views/menu/add")
public class MenuAddView implements BaseView {

    private MenuEntity menuInfo;

    private List<SelectInfo> menuHrefs;
}
