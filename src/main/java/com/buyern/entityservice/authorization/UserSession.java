package com.buyern.entityservice.authorization;

import lombok.Data;

import java.security.Principal;

@Data
public class UserSession implements Principal {
    private long id;
    private String uid;
    private String firstName;
    private String lastName;

    @Override
    public String getName() {
        return uid;
    }
}
