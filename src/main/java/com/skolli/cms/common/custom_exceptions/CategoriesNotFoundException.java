package com.skolli.cms.common.custom_exceptions;

public class CategoriesNotFoundException extends RuntimeException{
    public CategoriesNotFoundException(String message){
        super(message);
    }
}
