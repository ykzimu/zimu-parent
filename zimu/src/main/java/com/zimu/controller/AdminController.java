package com.zimu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.IdUtils;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.DataTablesView;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.SearchInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.MenuService;
import com.zimu.service.RoleService;
import com.zimu.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;


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
    @PostMapping("/dict/listData")
    @ResponseBody
    public DataTablesView dictListData(DataTablesInfo dataTablesInfo) {
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        PageInfo<UserEntity> page = userService.getUsers(dataTablesInfo);
        DataTablesView<UserEntity> dataTablesView = new DataTablesView(page);
        return dataTablesView;
    }

}
