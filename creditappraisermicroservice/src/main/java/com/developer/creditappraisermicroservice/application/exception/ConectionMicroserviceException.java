package com.developer.creditappraisermicroservice.application.exception;

import lombok.Getter;

@Getter
public class ConectionMicroserviceException extends Exception {

    private Integer status;
    public ConectionMicroserviceException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
