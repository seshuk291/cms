package com.skolli.cms.common.custom_exceptions;

public class OrderDeletionException extends RuntimeException {
    public OrderDeletionException(String message) {
        super(message);
    }
}