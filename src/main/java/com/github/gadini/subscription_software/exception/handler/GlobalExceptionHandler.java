package com.github.gadini.subscription_software.exception.handler;

import com.github.gadini.subscription_software.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseDto> handleAllExceptions(Exception ex, HttpServletRequest req) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), req);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    private ResponseEntity<ExceptionResponseDto> buildResponse(HttpStatus httpStatus, String message, HttpServletRequest req) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(new Date(), message, httpStatus.getReasonPhrase(), req.getRequestURI());
        return new ResponseEntity<>(exceptionResponseDto, httpStatus);
    }

}
