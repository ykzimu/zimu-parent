package com.zimu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cas.service")
public class CasServiceConfig {
	private String host;
	private String login;
	private String logout;
	private Boolean sendRenew = false;
}
