package com.zimu.domain.info;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.zimu.domain.entity.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserInfo extends UserEntity implements UserDetails, OAuth2User {

    private static final long serialVersionUID = 1L;

    private Set<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    private String name;

    private String login;

    private List<MenuInfo> menuInfos;

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        Integer flag = super.getIsExpired();
        if (flag != null && flag == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isAccountNonLocked() {
        Integer flag = super.getIsLocked();
        if (flag != null && flag == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        Integer flag = super.getIsCredentialsExpired();
        if (flag != null && flag == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEnabled() {
        Integer flag = super.getIsEnabled();
        if (flag != null && flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getName() {
        if (StringUtils.isBlank(name)) {
            return this.getUsername();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuInfo> getMenuInfos() {
        return menuInfos;
    }

    public void setMenuInfos(List<MenuInfo> menuInfos) {
        this.menuInfos = menuInfos;
    }
}
