package com.zimu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cas.server")
public class CasServerConfig {
	private String host;
	private String login;
	private String logout;
}
