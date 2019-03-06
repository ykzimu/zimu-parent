package com.zimu.config;

import com.zimu.interceptor.HttpServletInterceptor;
import com.zimu.listener.MuziServletContextListener;
import com.zimu.resolver.DataTablesHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableConfigurationProperties({WebMvcProperties.class})
public class ZimuWebConfig implements WebMvcConfigurer {

    @Autowired
    private WebMvcProperties webMvcProperties;

    /**
     * 自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpServletInterceptor())
            .excludePathPatterns(webMvcProperties.getStaticPathPattern());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DataTablesHandlerMethodArgumentResolver());
    }


    @Bean
    public ServletListenerRegistrationBean<MuziServletContextListener> addMuziServletContextListener() {
        ServletListenerRegistrationBean<MuziServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new MuziServletContextListener());
        return servletListenerRegistrationBean;
    }

}
