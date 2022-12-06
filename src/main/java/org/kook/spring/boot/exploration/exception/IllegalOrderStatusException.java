package org.kook.spring.boot.exploration.exception;

import org.kook.spring.boot.exploration.entity.Status;

public class IllegalOrderStatusException extends AppException {
    public IllegalOrderStatusException(Status status) {
        super(status.toString());
    }
}
