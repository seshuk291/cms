package com.skolli.cms.common.custom_exceptions;

public class UserDeletionException extends RuntimeException{
    public UserDeletionException(String message){
        super(message);
    }
}
