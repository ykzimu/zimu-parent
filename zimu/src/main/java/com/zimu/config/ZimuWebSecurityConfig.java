package com.zimu.config;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.zimu.component.RoleComponent;
import com.zimu.domain.info.UserInfo;
import com.zimu.security.UserInfoOauth2UserService;

@Configuration
@EnableWebSecurity
public class ZimuWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private RoleComponent roleComponent;

	@Autowired
	private CasAuthenticationEntryPoint casAuthenticationEntryPoint;

	@Autowired
	private CasAuthenticationProvider casAuthenticationProvider;

	@Autowired
	private CasServerConfig casServerConfig;

	@Autowired
	private CasServiceConfig casServiceConfig;

	@Autowired
	private UserInfoOauth2UserService userInfoOAuth2UserService;

	/**
	 * 配置信息
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		Map<String, String[]> roleMenus = roleComponent.getRoleMenus();

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.authorizeRequests()
				// 静态资源及无需登录的资源
				.antMatchers("/", "/index", "/static/**", "/test/**", "/public/**", "/register/**").permitAll();

		// 遍历数据库中变更的权限
		Iterator<Map.Entry<String, String[]>> iterator = roleMenus.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = iterator.next();
			registry = registry.antMatchers(entry.getKey()).hasAnyRole(entry.getValue());
		}
		//
		registry.anyRequest().authenticated()//
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()//
				// oauth2Login登录
				.and().oauth2Login().loginPage("/auth/login").defaultSuccessUrl("/").userInfoEndpoint()
				.customUserType(UserInfo.class, "github").customUserType(UserInfo.class, "baidu")// .userAuthoritiesMapper(userAuthoritiesMapper())
				.userService(userInfoOAuth2UserService).and().permitAll().and().httpBasic()//
				.and().csrf().disable();

		http.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint)//
				.and()//
				.addFilter(this.casAuthenticationFilter())//
				.addFilterBefore(this.logoutFilter(), LogoutFilter.class)//
				.addFilterBefore(this.singleSignOutFilter(), CasAuthenticationFilter.class);
	}

	/**
	 * 自定义登录构造
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider);
		// auth.authenticationProvider(zimuAuthenticationProvider);
	}

	private CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		casAuthenticationFilter.setFilterProcessesUrl(this.casServiceConfig.getLogin());
		casAuthenticationFilter.setContinueChainBeforeSuccessfulAuthentication(false);
		casAuthenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
		return casAuthenticationFilter;
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
}
