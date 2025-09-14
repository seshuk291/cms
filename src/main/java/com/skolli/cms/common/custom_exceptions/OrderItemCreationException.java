package com.skolli.cms.common.custom_exceptions;

public class OrderItemCreationException extends RuntimeException {
    public OrderItemCreationException(String message) {
        super(message);
    }
}