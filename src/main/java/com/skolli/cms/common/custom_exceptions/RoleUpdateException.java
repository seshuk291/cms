package com.skolli.cms.common.custom_exceptions;

public class RoleUpdateException extends RuntimeException {
    public RoleUpdateException(String message) {
        super(message);
    }
}