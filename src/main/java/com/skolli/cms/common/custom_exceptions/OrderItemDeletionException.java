package com.skolli.cms.common.custom_exceptions;

public class OrderItemDeletionException extends RuntimeException {
    public OrderItemDeletionException(String message) {
        super(message);
    }
}