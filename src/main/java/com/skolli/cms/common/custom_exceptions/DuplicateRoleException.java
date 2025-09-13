package com.skolli.cms.common.custom_exceptions;

public class DuplicateRoleException extends RuntimeException {
    public DuplicateRoleException(String message) {
        super(message);
    }
}