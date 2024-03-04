package com.amazonagency.utils;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
        protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
            HttpStatus status = ex instanceof IllegalArgumentException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), status, request);
        }
}
