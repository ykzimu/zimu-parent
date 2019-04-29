package com.zimu;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zimu.entity.MenuEntity;
import com.zimu.entity.UserEntity;
import com.zimu.service.MenuService;
import com.zimu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZimuApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void contextLoads() {
        List<MenuEntity> list = menuService.list(Wrappers.emptyWrapper());
    }

}
