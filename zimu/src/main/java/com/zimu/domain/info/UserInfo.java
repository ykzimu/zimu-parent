package com.zimu.domain.info;

import com.zimu.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends UserEntity implements UserDetails, OAuth2User, Serializable {

    private static final long serialVersionUID = 1L;

    private Set<GrantedAuthority> authorities;

    private Map<String, Object> attributes;

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
    public boolean isAccountNonExpired() {
        Integer flag = getIsExpired();
        return flag == null || flag != 1;

    }

    @Override
    public boolean isAccountNonLocked() {
        Integer flag = getIsLocked();
        return flag == null || flag != 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        Integer flag = getIsCredentialsExpired();
        return flag == null || flag != 1;
    }

    @Override
    public boolean isEnabled() {
        Integer flag = getIsEnabled();
        return flag != null && flag == 1;
    }

    @Override
    public String getName() {
        return getUsername();
    }

}
