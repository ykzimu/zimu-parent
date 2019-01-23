package com.zimu.config;

import lombok.Data;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "memcached")
@Configuration
public class MemcachedConfig {

    private String servers;

    private String protocol;

    private Long opTimeout;

    private Integer timeoutExceptionThreshold;

    private String locatorType;

    private String failureMode;

    private Boolean useNagleAlgorithm;

    @Bean
    public MemcachedClientFactoryBean memcachedClient() {
        MemcachedClientFactoryBean mcFactoryBean = new MemcachedClientFactoryBean();
        mcFactoryBean.setServers(servers);
        mcFactoryBean.setProtocol(ConnectionFactoryBuilder.Protocol.valueOf(protocol));
        mcFactoryBean.setFailureMode(FailureMode.valueOf(failureMode));
        mcFactoryBean.setOpTimeout(opTimeout);
        mcFactoryBean.setTimeoutExceptionThreshold(timeoutExceptionThreshold);
        mcFactoryBean.setLocatorType(ConnectionFactoryBuilder.Locator.valueOf(locatorType));
        mcFactoryBean.setUseNagleAlgorithm(useNagleAlgorithm);
        return mcFactoryBean;
    }
}
