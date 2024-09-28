package br.com.api.dealership.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
    public IdNotFoundException() {
        super("Id not found");
    }
}
