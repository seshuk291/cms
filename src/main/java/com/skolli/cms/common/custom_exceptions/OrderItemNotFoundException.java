package com.skolli.cms.common.custom_exceptions;

public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}