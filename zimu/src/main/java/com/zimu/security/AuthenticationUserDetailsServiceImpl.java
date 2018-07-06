package com.zimu.security;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;

@Component("authenticationUserDetailsServiceImpl")
public class AuthenticationUserDetailsServiceImpl
		implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		try {
			
			//TODO 实现获取用户信息
            Map<String, Object> userAttributess = token.getAssertion().getPrincipal().getAttributes();

			// 查询用户信息
			UserEntity userEntity = userService.getUserByUsername(token.getName());
			if (userEntity == null) {
				throw new UsernameNotFoundException("username not found.");
			}

			// 查询角色信息
			List<String> roles = userService.getRolesByUserId(userEntity.getId());
			Set<GrantedAuthority> authorities = new HashSet<>();
			SimpleGrantedAuthority grantedAuthority = null;
			for (String role : roles) {
				grantedAuthority = new SimpleGrantedAuthority(role);
				authorities.add(grantedAuthority);
			}
			UserInfo userInfo = new UserInfo();
			BeanUtils.copyProperties(userInfo, userEntity);
			userInfo.setAuthorities(authorities);
			return userInfo;
		} catch (Exception e) {
			throw new UsernameNotFoundException("username not found.");
		}
	}

}