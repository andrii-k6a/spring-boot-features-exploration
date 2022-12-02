package org.kook.spring.boot.exploration.exception;

public class EmployeeNotFoundException extends AppException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
