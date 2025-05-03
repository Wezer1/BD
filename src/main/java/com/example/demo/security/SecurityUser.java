package com.example.demo.security;

import com.example.demo.entity.User;
import com.example.demo.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;
    private final Integer userId;
    private final Role role;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive, Integer userId, Role role) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
        this.userId = userId;
        this.role = role;
    }

    public static UserDetails fromToken(Role role, String email, Integer userId){
        return new SecurityUser(email,"", getAuthority(role), true, userId,role);
    }

    public static UserDetails fromUser(User user){
        return new SecurityUser(user.getName(), user.getPassword(),getAuthority(user.getRole()),
                true, user.getId(), user.getRole());
    }

    public static List<SimpleGrantedAuthority> getAuthority(Role role){
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role.name()));
        return authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
