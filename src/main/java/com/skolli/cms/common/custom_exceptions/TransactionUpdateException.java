package com.skolli.cms.common.custom_exceptions;

public class TransactionUpdateException extends RuntimeException {
    public TransactionUpdateException(String message) {
        super(message);
    }
}