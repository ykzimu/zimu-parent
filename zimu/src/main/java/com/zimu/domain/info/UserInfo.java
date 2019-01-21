package com.zimu.domain.info;

import com.zimu.domain.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends UserEntity implements UserDetails, OAuth2User {

    private static final long serialVersionUID = 1L;

    private Set<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    private String name;

    private String login;

    private List<MenuInfo> menuInfos;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
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

    @Override
    public String getName() {
        if (StringUtils.isBlank(name)) {
            return this.getUsername();
        }
        return name;
    }

}
