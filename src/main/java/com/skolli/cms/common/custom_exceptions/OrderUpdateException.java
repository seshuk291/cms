package com.skolli.cms.common.custom_exceptions;

public class OrderUpdateException extends RuntimeException {
    public OrderUpdateException(String message) {
        super(message);
    }
}