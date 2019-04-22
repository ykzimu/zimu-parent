package com.zimu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.IdUtils;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.info.*;
import com.zimu.entity.RoleEntity;
import com.zimu.entity.UserEntity;
import com.zimu.service.MenuService;
import com.zimu.service.RoleService;
import com.zimu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
        IPage<UserEntity> page = userService.getUsers(searchInfo);
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
        IPage<RoleEntity> page = roleService.getRoles();
        mv.addObject("page", page);
        return mv;
    }

}
