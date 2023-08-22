package com.mo.responseentity.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractException extends RuntimeException {

    public Map<String, String> validation = new HashMap<>();
    public AbstractException(String message) {
        super(message);
    }
    public abstract int statusCode();
}
