package com.zimu.config;

import com.zaxxer.hikari.HikariDataSource;
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
    @ConfigurationProperties("spring.datasource.hikari.zimu")
    public DataSource zimuDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @QuartzDataSource
    @ConfigurationProperties("spring.datasource.hikari.quartz")
    public DataSource quartzDataSource() {
        return new HikariDataSource();
    }

}
