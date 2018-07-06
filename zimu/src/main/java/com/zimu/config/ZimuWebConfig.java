package com.zimu.config;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zimu.filter.MySiteMeshFilter;
import com.zimu.interceptor.HttpServletInterceptor;
import com.zimu.interceptor.MenuInterceptor;

@Configuration
@EnableConfigurationProperties({WebMvcProperties.class, CasServerConfig.class, CasServiceConfig.class})
public class ZimuWebConfig implements WebMvcConfigurer {

    @Autowired
    private WebMvcProperties webMvcProperties;

    @Autowired
    private CasServerConfig casServerConfig;

    @Autowired
    private CasServiceConfig casServiceConfig;

    /**
     * 自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpServletInterceptor())
            .excludePathPatterns(webMvcProperties.getStaticPathPattern());
        registry.addInterceptor(new MenuInterceptor()).excludePathPatterns(webMvcProperties.getStaticPathPattern());
    }

    /**
     * MySiteMeshFilter 用于模板生产
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<MySiteMeshFilter> addMySiteMeshFilter() {
        FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<MySiteMeshFilter>();
        filterRegistrationBean.setFilter(new MySiteMeshFilter());
        filterRegistrationBean.setName("siteMeshFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

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
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> addSingleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(this.casServiceConfig.getHost() + this.casServiceConfig.getLogin());
        serviceProperties.setSendRenew(this.casServiceConfig.getSendRenew());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(this.casServerConfig.getLogin());
        entryPoint.setServiceProperties(serviceProperties);
        return entryPoint;
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas30ServiceTicketValidator(this.casServerConfig.getHost());
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(
        AuthenticationUserDetailsService<CasAssertionAuthenticationToken> userDetailsService,
        ServiceProperties serviceProperties, Cas20ServiceTicketValidator ticketValidator) {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setKey("casProvider");
        provider.setServiceProperties(serviceProperties);
        provider.setTicketValidator(ticketValidator);
        provider.setAuthenticationUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

}
