package com.zimu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.DataTablesView;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.MenuInfo;
import com.zimu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表
     *
     * @return ModelAndView
     */
    @PostMapping("/listData")
    @ResponseBody
    public DataTablesView listData(DataTablesInfo dataTablesInfo) {
        IPage<MenuInfo> page = menuService.listData();
        return new DataTablesView<>(page);
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @PostMapping("/change")
    public JsonView change(Long id, Boolean delFlag, Boolean isShow) {
        JsonView jsonView = new JsonView();
        Object data = menuService.changeMenuStatusById(id, delFlag, isShow);
        jsonView.setData(data);
        return jsonView;

    }

    /**
     * 保存菜单排序
     *
     * @return ModelAndView
     */
    @PostMapping("/sort")
    public JsonView sort(String value) {
        JsonView jsonView = new JsonView();
        // 空不做操作
        if (StringUtils.isBlank(value)) {
            return jsonView;
        }

        menuService.sortMenu(value);
        return jsonView;
    }
}
