package com.skolli.cms.common.custom_exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message) {
        super(message);
    }
    
}
