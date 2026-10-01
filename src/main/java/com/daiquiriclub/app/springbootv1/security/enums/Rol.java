package com.daiquiriclub.app.springbootv1.security.enums;

import java.util.Set;

public enum Rol {
    ADMINISTRADOR(
            Set.of(
                    Permission.CATEGORY_READ,
                    Permission.CATEGORY_CREATE,
                    Permission.CATEGORY_UPDATE,
                    Permission.CATEGORY_DELETE
            )
    ),
    USER(Set.of()),
    VENDEDOR(Set.of());

    private final Set<Permission> permissions;

    Rol(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<Permission> getPermissions(){
        return permissions;
    }
}
