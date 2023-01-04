package br.com.tinnova.vehiclesrecords.exceptions.handler;

import br.com.tinnova.vehiclesrecords.exceptions.EntityInUseException;
import br.com.tinnova.vehiclesrecords.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;
@ControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(ResourceNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardApiError error = errorBuilder(status, ErrorType.ENTITY_NOT_FOUND, e.getMessage()).build();

        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUse(EntityInUseException e, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardApiError error = errorBuilder(status, ErrorType.ENTITY_IN_USE, e.getMessage()).build();

        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException)  {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        StandardApiError error = errorBuilder(
                status,
                ErrorType.MESSAGE_NOT_READABLE,
                rootCause.getMessage())
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {
        ErrorType errorType = ErrorType.MESSAGE_NOT_READABLE;
        String reference = ex.getPath().stream().map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));

        String detail = String.format("Property '%s' got the value '%s' which is an invalid type. Please certify that value is type of %s",
                reference, ex.getValue(), ex.getTargetType().getSimpleName());

        StandardApiError error = errorBuilder(
                status,
                errorType,
                detail)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private StandardApiError.StandardApiErrorBuilder errorBuilder(HttpStatusCode status, ErrorType errorType, String detail) {
        return StandardApiError.builder()
                .status(status.value())
                .title(errorType.getTitle())
                .type(errorType.getUri())
                .detail(detail);
    }
}
