package com.skolli.cms.common.custom_exceptions;

public class PaymentMethodDeletionException extends RuntimeException {
    public PaymentMethodDeletionException(String message) {
        super(message);
    }
}