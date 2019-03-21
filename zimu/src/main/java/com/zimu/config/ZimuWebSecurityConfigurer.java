package com.zimu.config;

import com.zimu.component.RoleComponent;
import com.zimu.domain.info.UserInfo;
import com.zimu.security.UserInfoOauth2UserService;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class ZimuWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleComponent roleComponent;

    @Autowired
    private CasServerConfig casServerConfig;

    @Autowired
    private CasServiceConfig casServiceConfig;

    @Autowired
    private UserInfoOauth2UserService userInfoOAuth2UserService;

    @Autowired
    private StaticConfig staticConfig;

    @Autowired
    private AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService;

    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository;

    /**
     * 配置信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Map<String, String[]> roleMenus = roleComponent.getRoleMenus();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
            .authorizeRequests()
            // 静态资源及无需登录的资源
            .antMatchers(staticConfig.getUrlPatterns()).permitAll();

        // 遍历数据库中变更的权限
        for (Map.Entry<String, String[]> entry : roleMenus.entrySet()) {
            registry = registry.antMatchers(entry.getKey()).hasAnyRole(entry.getValue());
        }
        //
        registry.anyRequest().authenticated()//
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()//
            .and().headers().frameOptions().disable()//
            // oauth2Login登录
            .and().oauth2Login().loginPage("/auth/login").defaultSuccessUrl("/").userInfoEndpoint()
            .customUserType(UserInfo.class, "github").customUserType(UserInfo.class, "baidu")// .userAuthoritiesMapper(userAuthoritiesMapper())
            .userService(userInfoOAuth2UserService).and().permitAll().and().httpBasic()//
            .and().csrf().disable().rememberMe().rememberMeServices(rememberMeServices());//

        http.exceptionHandling().authenticationEntryPoint(this.casAuthenticationEntryPoint())//
            .and()//
            .addFilter(this.casAuthenticationFilter())//
            .addFilterBefore(this.logoutFilter(), LogoutFilter.class)//
            .addFilterBefore(this.singleSignOutFilter(), CasAuthenticationFilter.class)//
            .addFilterAfter(new HttpServletRequestWrapperFilter(), CasAuthenticationFilter.class);
    }

    /**
     * 自定义登录构造
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.casAuthenticationProvider());
    }

    private CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(super.authenticationManager());
        casAuthenticationFilter.setFilterProcessesUrl(this.casServiceConfig.getLogin());
        casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
        casAuthenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
        return casAuthenticationFilter;
    }

    private ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(this.casServiceConfig.getHost() + this.casServiceConfig.getLogin());
        serviceProperties.setSendRenew(this.casServiceConfig.getSendRenew());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    private CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(this.casServerConfig.getLogin());
        entryPoint.setServiceProperties(this.serviceProperties());
        return entryPoint;
    }

    private CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setKey("casProvider");
        provider.setServiceProperties(this.serviceProperties());
        provider.setTicketValidator(new Cas30ServiceTicketValidator(this.casServerConfig.getHost()));
        provider.setAuthenticationUserDetailsService(authenticationUserDetailsService);
        return provider;
    }

    private LogoutFilter logoutFilter() {
        String logoutRedirectPath = this.casServerConfig.getLogout() + "?service=" + this.casServiceConfig.getHost();
        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath, new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(this.casServiceConfig.getLogout());
        return logoutFilter;
    }

    private SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(this.casServerConfig.getHost());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new SingleSignOutHttpSessionListener());
        return servletListenerRegistrationBean;
    }

}
