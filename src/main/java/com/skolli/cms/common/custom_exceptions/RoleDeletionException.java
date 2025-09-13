package com.skolli.cms.common.custom_exceptions;

public class RoleDeletionException extends RuntimeException {
    public RoleDeletionException(String message) {
        super(message);
    }
}