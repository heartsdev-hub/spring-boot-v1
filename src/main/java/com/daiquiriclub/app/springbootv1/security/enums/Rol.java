package com.daiquiriclub.app.springbootv1.security.enums;

import java.util.Set;

public enum Rol {
    ADMINISTRADOR(
            Set.of(
                    Permission.CATEGORY_READ,
                    Permission.CATEGORY_CREATE,
                    Permission.CATEGORY_UPDATE,
                    Permission.CATEGORY_DELETE,
                    Permission.CATEGORY_READ_ACTIVE,

                    Permission.PRODUCT_READ,
                    Permission.PRODUCT_CREATE,
                    Permission.PRODUCT_UPDATED,
                    Permission.PRODUCT_DELETE,

                    Permission.SUPPLIER__READ,
                    Permission.SUPPLIER_CREATE,
                    Permission.SUPPLIER_UPDATE,
                    Permission.SUPPLIER_DELETE,

                    Permission.USUARIO_READ,
                    Permission.USUARIO_CREATE,
                    Permission.USUARIO_UPDATE,
                    Permission.USUARIO_DELETE
            )
    ),
    USER(
            Set.of(
               Permission.CATEGORY_READ,
               Permission.CATEGORY_READ_ACTIVE,
               Permission.PRODUCT_READ,
               Permission.SUPPLIER__READ,
               Permission.USUARIO_READ,
               Permission.USUARIO_CREATE,
               Permission.USUARIO_UPDATE,
               Permission.USUARIO_DELETE
    )),
    VENDEDOR(
            Set.of(
                    Permission.CATEGORY_READ,
                    Permission.CATEGORY_READ_ACTIVE,
                    Permission.PRODUCT_READ,
                    Permission.SUPPLIER__READ,
                    Permission.SUPPLIER_CREATE,
                    Permission.SUPPLIER_UPDATE,
                    Permission.SUPPLIER_DELETE
            ));

    private final Set<Permission> permissions;

    Rol(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<Permission> getPermissions(){
        return permissions;
    }
}
