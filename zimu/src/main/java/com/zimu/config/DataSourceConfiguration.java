package com.zimu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({DataSourceProperties.class, QuartzDataSourceProperties.class})
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSource zimuDataSource(DataSourceProperties properties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setName(properties.getName());
        return druidDataSource;
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource(QuartzDataSourceProperties properties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setName(properties.getName());
        return druidDataSource;
    }

}
