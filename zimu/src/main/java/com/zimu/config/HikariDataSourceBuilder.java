package com.zimu.config;

import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceBuilder {

    public static HikariDataSourceBuilder create() {
        return new HikariDataSourceBuilder();
    }

    public HikariDataSource build() {
        return new HikariDataSource();
    }
}
