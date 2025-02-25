package com.example.demo.model;

public enum Permission {
    CLIENTS_READ("clients:read"),
    CLIENTS_WRITE("clients:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
