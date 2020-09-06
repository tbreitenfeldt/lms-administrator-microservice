package com.smoothstack.lms.adminservice.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class AdministratorRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AdministratorRestExceptionHandler.class);

    @ExceptionHandler(value = ArgumentMissingException.class)
    public ResponseEntity<ApiError> handleException(ArgumentMissingException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Missing argument", exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> handleException(BadRequestException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Reference not found", exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = ExtraneousIdException.class)
    public ResponseEntity<ApiError> handleException(ExtraneousIdException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Reference not found", exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }


    @ExceptionHandler(value = IllegalRelationReferenceException.class)
    public ResponseEntity<ApiError> handleException(IllegalRelationReferenceException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Reference not found", exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<ApiError> handleException(ItemNotFoundException exception) {
        logger.error(exception.toString());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred",
                exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleException(ResourceAlreadyExistsException exception) {
        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Item already exists",
                exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        logger.error(exception.toString());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "error occurred",
                exception.getLocalizedMessage());
        logger.error(apiError);
        return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
