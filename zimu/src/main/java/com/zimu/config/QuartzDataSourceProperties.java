package com.zimu.config;

import lombok.Data;
import org.quartz.utils.PoolingConnectionProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.quartz.datasource")
@Data
public class QuartzDataSourceProperties {

    /**
     * Name of the datasource. Default to "testdb" when using an embedded database.
     */
    private String name;


    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC URL of the database.
     */
    private String url;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    private int maxConnections = PoolingConnectionProvider.DEFAULT_DB_MAX_CONNECTIONS;

    private String validationQuery;

}
