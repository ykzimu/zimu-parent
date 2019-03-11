package com.zimu;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.muzi.config.MuziAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZimuApplicationTests {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired(required = false)
    private MuziAutoConfiguration muziAutoConfiguration;

    @Test
    public void contextLoads() {
        String[] names = applicationContext.getBeanDefinitionNames();
        List<String> nameArr = Arrays.asList(names);
        nameArr.forEach(System.out::println);
    }

}
