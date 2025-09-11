package com.skolli.cms.common.custom_exceptions;

public class ProductNotFoundException extends RuntimeException{
    ProductNotFoundException(String message) {
        super(message);
    }
}
