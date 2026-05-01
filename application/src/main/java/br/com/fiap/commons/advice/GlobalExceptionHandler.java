package br.com.fiap.commons.advice;

import br.com.fiap.commons.exception.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handle(MethodArgumentTypeMismatchException e) {
        log.warn(e.getMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, e.getMessage());
        if (e.getPropertyName() != null)
            problemDetail.setProperty(e.getPropertyName(), e.getCause().getMessage());
        return problemDetail;
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ProblemDetail handle(MissingRequestHeaderException e) {
        log.warn(e.getMessage());
        return ProblemDetail.forStatusAndDetail(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handle(ResourceNotFoundException e) {
        log.warn(e.getMessage());
        return ProblemDetail.forStatusAndDetail(UNPROCESSABLE_CONTENT, e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ProblemDetail handle(AlreadyExistsException e) {
        log.warn(e.getMessage());
        return ProblemDetail.forStatusAndDetail(UNPROCESSABLE_CONTENT, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handle(BusinessException e) {
        if (e.getCause() != null)
            log.warn(e.getMessage(), e);
        else
            log.warn(e.getMessage());
        return ProblemDetail.forStatusAndDetail(UNPROCESSABLE_CONTENT, e.getMessage());
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ProblemDetail handle(TooManyRequestsException e) {
        log.warn(e.getMessage());
        return ProblemDetail.forStatusAndDetail(TOO_MANY_REQUESTS, e.getMessage());
    }

    @ExceptionHandler(InternalErrorException.class)
    public ProblemDetail handle(InternalErrorException e) {
        log.error(e.getMessage(), e);
        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Internal error");
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handle(Exception e) {
        log.error(e.getMessage(), e);
        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, "Internal error");
    }

    @ExceptionHandler(IntegrationException.class)
    public ProblemDetail handle(IntegrationException e) {
        log.warn(e.getMessage(), e);
        return ProblemDetail.forStatusAndDetail(INTERNAL_SERVER_ERROR, e.getMessage());
    }
}