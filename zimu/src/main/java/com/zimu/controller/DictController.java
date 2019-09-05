package com.zimu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.DataTablesView;
import com.zimu.entity.UserEntity;
import com.zimu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private UserService userService;

    /**
     * 数据字典
     *
     * @return ModelAndView
     */
    @PostMapping("/listData")
    public DataTablesView listData(DataTablesInfo dataTablesInfo) {
        IPage<UserEntity> page = userService.getUsers(dataTablesInfo);
        return new DataTablesView<>(page);
    }
}
