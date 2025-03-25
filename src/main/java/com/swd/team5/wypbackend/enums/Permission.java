package com.swd.team5.wypbackend.enums;

public enum Permission {
    PRODUCT_READ("product:read"),
    PRODUCT_CREATE("product:create"),
    PRODUCT_UPDATE("product:update"),
    PRODUCT_DELETE("product:delete"),
    STAFF_READ("staff:read"),
    STAFF_CREATE("staff:create"),
    STAFF_UPDATE("staff:update"),
    STAFF_DELETE("staff:delete"),
    PROMOTION_READ("promotion:read"),
    PROMOTION_CREATE("promotion:create"),
    PROMOTION_UPDATE("promotion:update"),
    PROMOTION_DELETE("promotion:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
