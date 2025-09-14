package com.skolli.cms.common.custom_exceptions;

public class TransactionCreationException extends RuntimeException {
    public TransactionCreationException(String message) {
        super(message);
    }
}