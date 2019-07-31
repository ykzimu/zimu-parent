package com.zimu.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.zimu")
    public DataSource zimuDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties("spring.datasource.druid.quartz")
    public DataSource quartzDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

}
