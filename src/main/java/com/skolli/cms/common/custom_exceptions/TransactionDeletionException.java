package com.skolli.cms.common.custom_exceptions;

public class TransactionDeletionException extends RuntimeException {
    public TransactionDeletionException(String message) {
        super(message);
    }
}