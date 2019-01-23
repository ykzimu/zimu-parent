package com.zimu.controller.admin;

import com.zimu.domain.info.JsonView;
import com.zimu.view.admin.DashboardIndexView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 仪表盘
 *
 * @author yk
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return DashboardIndexView.builder().build().view();
    }

    @GetMapping("sysInfo")
    @ResponseBody
    public JsonView sysInfo(){
        JsonView jsonView=new JsonView();

        return jsonView;
    }
}
