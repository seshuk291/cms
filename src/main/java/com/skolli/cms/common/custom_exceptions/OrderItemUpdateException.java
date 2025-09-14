package com.skolli.cms.common.custom_exceptions;

public class OrderItemUpdateException extends RuntimeException {
    public OrderItemUpdateException(String message) {
        super(message);
    }
}