package br.com.tinnova.vehiclesrecords.exceptions.handler;

import br.com.tinnova.vehiclesrecords.exceptions.EntityInUseException;
import br.com.tinnova.vehiclesrecords.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(ResourceNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardApiError error = errorBuilder(status, ErrorType.ENTITY_NOT_FOUND, e.getMessage());

        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardApiError error = errorBuilder(status, ErrorType.ENTITY_IN_USE, e.getMessage());

        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    private StandardApiError errorBuilder(HttpStatusCode status, ErrorType errorType, String detail) {
        return StandardApiError.builder()
                .status(status.value())
                .title(errorType.getTitle())
                .type(errorType.getUri())
                .detail(detail)
                .build();
    }
}
