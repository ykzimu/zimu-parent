package com.zimu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = CasProperties.CAS_PREFIX)
public class CasProperties {

    public static final String CAS_PREFIX = "cas";

    private Boolean enabled = false;

    private Server server;

    private Service service;

    @Data
    public static class Server {
        private String host;
        private String login;
        private String logout;
    }

    @Data
    public static class Service {
        private String host;
        private String login;
        private String logout;
        private Boolean sendRenew = false;
    }

}
