package com.skolli.cms.common.custom_exceptions;

public class OrderCreationException extends RuntimeException {
    public OrderCreationException(String message) {
        super(message);
    }
}