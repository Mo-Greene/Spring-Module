package com.mo.responseentity.exception;


public class CustomException extends AbstractException{
    public CustomException(String message) {
        super(message);
    }

    @Override
    public int statusCode() {
        return 400;
    }
}
