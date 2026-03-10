package com.example.demo.core.service.exception;

import lombok.Getter;

import java.util.Collection;

@Getter
public class CustomValidationException extends RuntimeException {

    private final Collection<String> errors;

    public CustomValidationException(Collection<String> errors,
                                     String message,
                                     Throwable throwable){
        super(message, throwable);
        this.errors = errors;
    }

    public CustomValidationException(Collection<String> errors,
                                     String message){
        super(message);
        this.errors = errors;
    }

}
