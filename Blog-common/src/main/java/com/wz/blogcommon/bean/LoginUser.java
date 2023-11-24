package com.wz.blogcommon.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author wazh
 * @since 2023-10-13-14:49
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -68421412707540L;

    private User user;

    private List<String> permission;

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    public LoginUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
