package com.skolli.cms.common.custom_exceptions;

public class PaymentMethodCreationException extends RuntimeException {
    public PaymentMethodCreationException(String message) {
        super(message);
    }
}