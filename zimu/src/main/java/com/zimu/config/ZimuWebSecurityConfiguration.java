package com.zimu.config;

import com.zimu.component.RoleComponent;
import com.zimu.domain.info.UserInfo;
import com.zimu.security.UserDetailsServiceImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Map;

/**
 * @author yk
 */
@Configuration
@EnableWebSecurity
public class ZimuWebSecurityConfiguration {


    @Configuration
    @ConditionalOnProperty(prefix = "cas", name = "enabled", havingValue = "false", matchIfMissing = true)
    static class NormalWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        @Autowired
        private RoleComponent roleComponent;

        @Autowired
        private StaticProperties staticProperties;

        @Autowired
        private RememberMeServices rememberMeServices;

        /**
         * 配置信息
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            Map<String, String[]> roleMenus = roleComponent.getRoleMenus();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                    .authorizeRequests()
                    // 静态资源及无需登录的资源
                    .antMatchers(staticProperties.getUrlPatterns()).permitAll();

            // 遍历数据库中变更的权限
            for (Map.Entry<String, String[]> entry : roleMenus.entrySet()) {
                registry = registry.antMatchers(entry.getKey()).hasAnyRole(entry.getValue());
            }
            //
            registry.anyRequest().authenticated()//
                    .and().formLogin().loginPage("/login").permitAll()
                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()//
                    .and().headers().frameOptions().disable()//
                    // oauth2Login登录
                    .and().oauth2Login().loginPage("/auth/login").defaultSuccessUrl("/").userInfoEndpoint()
                    .customUserType(UserInfo.class, "github").customUserType(UserInfo.class, "baidu")// .userAuthoritiesMapper(userAuthoritiesMapper())
                    .userService(userDetailsService).and().permitAll().and().httpBasic()//
                    .and().csrf().disable().rememberMe().rememberMeServices(rememberMeServices).alwaysRemember(true);
        }


        @Override
        public void configure(WebSecurity web) throws Exception {
            //解决静态资源被拦截的问题
            web.ignoring().antMatchers(staticProperties.getUrlPatterns());
        }

        /**
         * 自定义登录构造
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

    }

    @Configuration
    @ConditionalOnProperty(prefix = "cas", name = "enabled", havingValue = "true")
    @EnableConfigurationProperties({CasProperties.class})
    static class CasWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CasProperties casProperties;

        @Autowired
        private RoleComponent roleComponent;

        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        @Autowired
        private StaticProperties staticProperties;

        @Autowired
        private RememberMeServices rememberMeServices;


        /**
         * 配置信息
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            Map<String, String[]> roleMenus = roleComponent.getRoleMenus();

            ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                    .authorizeRequests()
                    // 静态资源及无需登录的资源
                    .antMatchers(staticProperties.getUrlPatterns()).permitAll();

            // 遍历数据库中变更的权限
            for (Map.Entry<String, String[]> entry : roleMenus.entrySet()) {
                registry = registry.antMatchers(entry.getKey()).hasAnyRole(entry.getValue());
            }
            //
            registry.anyRequest().authenticated()//
                    .and().logout().logoutSuccessHandler(urlLogoutSuccessHandler()).logoutRequestMatcher(logoutRequestMatcher()).permitAll()//
                    .and().headers().frameOptions().disable()//
                    // oauth2Login登录
                    .and().oauth2Login().loginPage("/auth/login").defaultSuccessUrl("/").userInfoEndpoint()
                    .customUserType(UserInfo.class, "github").customUserType(UserInfo.class, "baidu")// .userAuthoritiesMapper(userAuthoritiesMapper())
                    .userService(userDetailsService).and().permitAll().and().httpBasic()//
                    .and().csrf().disable().rememberMe().rememberMeServices(rememberMeServices);//

            http.exceptionHandling().authenticationEntryPoint(this.casAuthenticationEntryPoint())//
                    .and()//
                    .addFilter(this.casAuthenticationFilter())//
                    .addFilterBefore(this.singleSignOutFilter(), CasAuthenticationFilter.class)//
                    .addFilterAfter(new HttpServletRequestWrapperFilter(), CasAuthenticationFilter.class);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            //解决静态资源被拦截的问题
            web.ignoring().antMatchers(staticProperties.getUrlPatterns());
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
            casAuthenticationFilter.setFilterProcessesUrl(this.casProperties.getService().getLogin());
            casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
            casAuthenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
            return casAuthenticationFilter;
        }

        private ServiceProperties serviceProperties() {
            ServiceProperties serviceProperties = new ServiceProperties();
            serviceProperties.setService(this.casProperties.getService().getHost() + this.casProperties.getService().getLogin());
            serviceProperties.setSendRenew(this.casProperties.getService().getSendRenew());
            serviceProperties.setAuthenticateAllArtifacts(true);
            return serviceProperties;
        }

        private CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
            CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
            entryPoint.setLoginUrl(this.casProperties.getServer().getLogin());
            entryPoint.setServiceProperties(this.serviceProperties());
            return entryPoint;
        }

        private CasAuthenticationProvider casAuthenticationProvider() {
            CasAuthenticationProvider provider = new CasAuthenticationProvider();
            provider.setKey("casProvider");
            provider.setServiceProperties(this.serviceProperties());
            provider.setTicketValidator(new Cas30ServiceTicketValidator(this.casProperties.getServer().getHost()));
            provider.setAuthenticationUserDetailsService(userDetailsService);
            return provider;
        }

        private SimpleUrlLogoutSuccessHandler urlLogoutSuccessHandler() {
            String logoutRedirectPath = this.casProperties.getServer().getLogout() + "?service=" + this.casProperties.getService().getHost();
            SimpleUrlLogoutSuccessHandler urlLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
            urlLogoutSuccessHandler.setDefaultTargetUrl(logoutRedirectPath);
            return urlLogoutSuccessHandler;
        }

        private SingleSignOutFilter singleSignOutFilter() {
            SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
            singleSignOutFilter.setCasServerUrlPrefix(this.casProperties.getServer().getHost());
            singleSignOutFilter.setIgnoreInitConfiguration(true);
            return singleSignOutFilter;
        }

        private RequestMatcher logoutRequestMatcher() {
            return new AntPathRequestMatcher(this.casProperties.getService().getLogout());
        }

        @Bean
        public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
            return new ServletListenerRegistrationBean<>(new SingleSignOutHttpSessionListener());
        }
    }

}
