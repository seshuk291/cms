package com.skolli.cms.common.custom_exceptions;

public class PaymentMethodUpdateException extends RuntimeException {
    public PaymentMethodUpdateException(String message) {
        super(message);
    }
}