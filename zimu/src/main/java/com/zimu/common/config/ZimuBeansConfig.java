package com.zimu.common.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class ZimuBeansConfig {

	/**
	 * 添加监听器
	 *
	 * @return RequestContextListener
	 */
	@Bean
	public ServletListenerRegistrationBean<RequestContextListener> addRequestContextListener() {
		ServletListenerRegistrationBean<RequestContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(new RequestContextListener());
		return servletListenerRegistrationBean;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
}
