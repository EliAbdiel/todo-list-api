package com.eliabdiel.infra.util;

public class UserRole {

    public static final String ROLE_INDEX = "ROLE_";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static final String HAS_ROLE_ADMIN = String.format("hasRole('%s')", ADMIN);
    public static final String HAS_ROLE_USER = String.format("hasRole('%s')", USER);
}