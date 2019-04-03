package com.zimu.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.*;
import com.zimu.service.MenuService;
import com.zimu.service.RequestMappingService;
import com.zimu.view.admin.MenuAddView;
import com.zimu.view.admin.MenuListView;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RequestMappingService requestMappingService;


    /**
     * 菜单列表
     *
     * @return ModelAndView
     */
    @GetMapping("/list")
    public ModelAndView list() {
        // 视图
        return MenuListView.builder().build().view();
    }

    /**
     * 菜单列表
     *
     * @return ModelAndView
     */
    @PostMapping("/listData")
    @ResponseBody
    public DataTablesView listData(DataTablesInfo dataTablesInfo) {
        PageInfo<MenuInfo> page = menuService.listData();
        return new DataTablesView<>(page);
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @GetMapping("/add")
    public ModelAndView add(Long id) {
        // 视图
        ModelAndView mv = new ModelAndView();
        if (id == null) {
            mv.setViewName("redirect:/admin/menu/list");
            return mv;
        }
        MenuEntity menuEntity = menuService.getMenuById(id);
        if (id != 0L && menuEntity == null) {
            mv.setViewName("redirect:/admin/menu/list");
            return mv;
        }

        List<SelectInfo> list = requestMappingService.getUrls();

        mv = MenuAddView.builder()//
            .menuHrefs(list)//
            .menuInfo(menuEntity)//
            .build().view();
        return mv;
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView save(MenuEntity menuEntity) {
        // 视图
        ModelAndView mv = new ModelAndView("redirect:/success");
        menuService.saveMenu(menuEntity);
        mv.addObject("message", "添加成功！");
        return mv;
    }

    /**
     * 修改菜单
     *
     * @return ModelAndView
     */
    @GetMapping("/edit")
    public ModelAndView edit(Long id) {
        // 视图
        ModelAndView mv = new ModelAndView("/views/menu/edit");
        if (id == null) {
            mv.setViewName("redirect:/admin/menu/list");
            return mv;
        }
        MenuEntity menuEntity = menuService.getMenuById(id);
        if (menuEntity == null) {
            mv.setViewName("redirect:/admin/menu/list");
            return mv;
        }
        List<SelectInfo> list = requestMappingService.getUrls();
        mv.addObject("menuInfo", menuEntity);
        mv.addObject("menuHrefs", list);
        return mv;
    }

    /**
     * 更新菜单
     *
     * @return ModelAndView
     */
    @PostMapping(value = "/update")
    public ModelAndView update(MenuEntity menuEntity) {
        // 视图
        ModelAndView mv = new ModelAndView("redirect:/success");
        menuService.updateMenu(menuEntity);
        mv.addObject("message", "更新成功！");
        return mv;
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @PostMapping("/change")
    @ResponseBody
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
    @ResponseBody
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
