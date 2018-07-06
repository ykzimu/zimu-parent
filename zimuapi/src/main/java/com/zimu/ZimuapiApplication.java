package com.zimu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.zimu.dao")
@EnableCaching
public class ZimuapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZimuapiApplication.class, args);
    }
}
