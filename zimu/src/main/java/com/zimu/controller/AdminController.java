package com.zimu.controller;

import com.github.pagehelper.PageInfo;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.HttpServletManager;
import com.zimu.common.utils.IdUtils;
import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.*;
import com.zimu.service.MenuService;
import com.zimu.service.RequestMappingService;
import com.zimu.service.RoleService;
import com.zimu.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;

    @Resource
    private RequestMappingService requestMappingService;

    /**
     * 用户列表页
     *
     * @param searchInfo 查询条件
     * @return ModelAndView
     */
    @RequestMapping("/user/list")
    public ModelAndView userList(SearchInfo searchInfo) {

        // 视图
        ModelAndView mv = new ModelAndView("/views/user/list");
        PageInfo<UserEntity> page = userService.getUsers(searchInfo);
        mv.addObject("searchInfo", searchInfo);
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 删除用户
     *
     * @param ids ids
     * @return ModelAndView
     */
    @RequestMapping("/user/delete")
    @ResponseBody
    public JsonView deleteUsers(String ids) {
        JsonView jsonView = new JsonView();

        List<Long> list = IdUtils.getIds(ids);
        if (list == null) {
            throw new ValidationException("请选择！");
        }
        Object data = userService.deleteUserByIds(list);
        jsonView.setData(data);

        return jsonView;
    }

    /**
     * 菜单列表
     *
     * @return ModelAndView
     */
    @GetMapping("/menu/list")
    public ModelAndView menuList() {
        // 视图
        ModelAndView mv = new ModelAndView("/views/menu/list");
        PageInfo<MenuEntity> page = menuService.getMenus();
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @GetMapping("/menu/add")
    public ModelAndView addMenu(Long id) {
        // 视图
        ModelAndView mv = new ModelAndView("/views/menu/add");
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
        mv.addObject("menuInfo", menuEntity);
        mv.addObject("menuHrefs", list);
        return mv;
    }

    /**
     * 添加菜单
     *
     * @return ModelAndView
     */
    @PostMapping("/menu/save")
    public ModelAndView saveMenu(MenuEntity menuEntity) {
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
    @GetMapping("/menu/edit")
    public ModelAndView editMenu(Long id) {
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
    @PostMapping(value = "/menu/update")
    public ModelAndView updateMenu(MenuEntity menuEntity) {
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
    @PostMapping("/menu/change")
    @ResponseBody
    public JsonView deleteMenu(Long id, Boolean delFlag, Boolean isShow) {

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
    @PostMapping("/menu/sort")
    @ResponseBody
    public JsonView sortMenu(String value) {
        JsonView jsonView = new JsonView();

        // 空不做操作
        if (StringUtils.isBlank(value)) {
            return jsonView;
        }

        menuService.sortMenu(value);
        return jsonView;
    }

    /**
     * 角色列表
     *
     * @return ModelAndView
     */
    @GetMapping("/role/list")
    public ModelAndView roleList() {
        // 视图
        ModelAndView mv = new ModelAndView("/views/role/list");
        PageInfo<RoleEntity> page = roleService.getRoles();
        mv.addObject("page", page);
        return mv;
    }

    /**
     * 数据字典列表
     *
     * @return ModelAndView
     */
    @GetMapping("/dict/list")
    public ModelAndView dictList() {
        ModelAndView mv = new ModelAndView("/views/dict/list");
        return mv;
    }

    /**
     * 数据字典
     *
     * @return ModelAndView
     */
    @GetMapping("/dict/listData")
    @ResponseBody
    public JsonView dictListData(SearchInfo searchInfo) {
        JsonView jsonView = new JsonView();
        PageInfo<UserEntity> page = userService.getUsers(searchInfo);
        jsonView.setData(page);
        return jsonView;
    }

    /**
     * 数据字典
     *
     * @return ModelAndView
     */
    @PostMapping("/dict/listDataTable")
    @ResponseBody
    public DataTablesView dictListDataTable(DataTablesInfo dataTablesInfo) {
        PageInfo<UserEntity> page = userService.getUsers(dataTablesInfo);
        DataTablesView<UserEntity> dataTablesView = new DataTablesView(page);
        return dataTablesView;
    }

}
