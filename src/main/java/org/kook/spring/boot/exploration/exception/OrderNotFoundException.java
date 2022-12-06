package org.kook.spring.boot.exploration.exception;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(Long id) {
        super("Could not find order " + id);
    }
}
