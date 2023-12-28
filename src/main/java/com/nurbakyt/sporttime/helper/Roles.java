package com.nurbakyt.sporttime.helper;

public enum Roles {
    ADMIN ("ROLE_ADMIN"),
    USER ("ROLE_USER");

    private final String roleName;


    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
