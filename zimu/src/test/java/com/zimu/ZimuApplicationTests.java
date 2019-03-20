package com.zimu;

import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZimuApplicationTests {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MemcachedClient memcachedClient;


    @Test
    public void contextLoads() {
        String[] names = applicationContext.getBeanDefinitionNames();
        List<String> nameArr = Arrays.asList(names);
        nameArr.forEach(System.out::println);
    }

}
