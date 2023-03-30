package com.springboot.demo.springbootdemoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum UserRole {
    ROLE_EMPLOYEE("ROLE_EMPLOYEE"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String value;
}
