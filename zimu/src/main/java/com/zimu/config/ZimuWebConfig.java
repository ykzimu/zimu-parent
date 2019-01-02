package com.zimu.config;

import com.zimu.filter.MySiteMeshFilter;
import com.zimu.interceptor.HttpServletInterceptor;
import com.zimu.interceptor.MenuInterceptor;
import com.zimu.listener.MuziServletContextListener;
import com.zimu.resolver.DataTablesHandlerMethodArgumentResolver;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
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
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableConfigurationProperties({WebMvcProperties.class})
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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DataTablesHandlerMethodArgumentResolver());
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

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> addSingleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<MuziServletContextListener> addMuziServletContextListener() {
        ServletListenerRegistrationBean<MuziServletContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new MuziServletContextListener());
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
    public LogoutFilter logoutFilter() {
        String logoutRedirectPath = this.casServerConfig.getLogout() + "?service=" + this.casServiceConfig.getHost();
        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(this.casServiceConfig.getLogout());
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(this.casServerConfig.getHost());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @Bean
    public HttpServletRequestWrapperFilter httpServletRequestWrapperFilter() {
        return new HttpServletRequestWrapperFilter();
    }

}
