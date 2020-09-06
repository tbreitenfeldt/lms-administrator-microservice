package com.smoothstack.lms.adminservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 8259464291427304283L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
