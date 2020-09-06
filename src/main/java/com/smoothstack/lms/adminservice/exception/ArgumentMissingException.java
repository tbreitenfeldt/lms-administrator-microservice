package com.smoothstack.lms.adminservice.exception;

public class ArgumentMissingException extends RuntimeException {
    private static final long serialVersionUID = 4061998380592216571L;

    public ArgumentMissingException(String message) {
        super(message);
    }

}
