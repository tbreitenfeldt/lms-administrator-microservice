package com.smoothstack.lms.adminservice.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7633656047086597213L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}
