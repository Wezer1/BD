package com.example.demo.model;


//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.CLIENTS_READ)),
    ADMIN(Set.of(Permission.CLIENTS_WRITE, Permission.CLIENTS_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    //с помощью SimpleGrantedAuthority security может определить кто и к чему может иметь доступ
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());//производим наш permission в SimpleGrantedAuthority
    }
}
