package com.developer.creditappraisermicroservice.application.exception;

public class ClientDataNotFound extends Exception {
    public ClientDataNotFound() {
        super("Client data not found with this cpf");
    }
}
