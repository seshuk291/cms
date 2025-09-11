package com.skolli.cms.common.custom_exceptions;

public class ProductCreationException extends RuntimeException{
    public ProductCreationException(String message){
        super(message);
    }
}
