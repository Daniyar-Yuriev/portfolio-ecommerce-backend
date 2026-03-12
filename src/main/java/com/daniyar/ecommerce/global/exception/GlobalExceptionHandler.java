package com.daniyar.ecommerce.global.exception;

import com.daniyar.ecommerce.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {

        ApiResponse<?> response = ApiResponse.builder()
                .status(500)
                .message(e.getMessage())
                .data(null)
                .build();

        return ResponseEntity
                .status(500)
                .body(response);
    }
}