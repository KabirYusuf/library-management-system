package com.lms.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AdminException.class)
    public ResponseEntity<?> handleBookErrorEx(AdminException ex, WebRequest request){
        ApiErrorDetail apiErrorDetail = new ApiErrorDetail(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiErrorDetail, HttpStatus.NOT_FOUND);
    }
}
