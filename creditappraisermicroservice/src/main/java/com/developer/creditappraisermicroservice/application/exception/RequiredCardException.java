package com.developer.creditappraisermicroservice.application.exception;

public class RequiredCardException extends RuntimeException{
    public RequiredCardException() {
        super("Error to send card to queue");
    }
}
