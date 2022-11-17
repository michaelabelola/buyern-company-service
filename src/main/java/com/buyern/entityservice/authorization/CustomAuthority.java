package com.buyern.entityservice.authorization;

import org.springframework.security.core.GrantedAuthority;


public
class CustomAuthority implements GrantedAuthority {
    private final String authority;

    public CustomAuthority(String authority) {
        this.authority = authority;
    }

    public CustomAuthority(Long entityId, Long tool, Permission permission) {
        this.authority = entityId + "/" + tool + "/" + permission;
    }

    public CustomAuthority fromAuthorityString(String authorityString) {
        String[] values = authorityString.split("/");
        return new CustomAuthority(Long.valueOf(values[0]), Long.valueOf(values[1]), Permission.valueOf(values[2]));
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
