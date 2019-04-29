package com.zimu.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
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
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(properties.getDriverClassName());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setPoolName(properties.getName());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @QuartzDataSource
    public DataSource quartzDataSource(QuartzDataSourceProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(properties.getDriverClassName());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setPoolName(properties.getName());
        if (properties.getMaxConnections() >= 1) {
            hikariConfig.setMaximumPoolSize(properties.getMaxConnections());
        }
        if (StringUtils.isNotBlank(properties.getValidationQuery())) {
            hikariConfig.setConnectionTestQuery(properties.getValidationQuery());
        }
        return new HikariDataSource(hikariConfig);
    }

}
