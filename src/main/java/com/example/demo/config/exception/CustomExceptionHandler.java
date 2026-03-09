package com.example.demo.config.exception;

import com.example.demo.core.service.exception.CustomValidationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> error(Exception ex){
        var detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), "Internal error");
        return ResponseEntity.internalServerError().body(detail);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> illegalArgument(IllegalArgumentException ex){
        var detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(detail);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<CustomProblemDetail> validationException(CustomValidationException ex){
        var detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getLocalizedMessage());
        var response = new CustomProblemDetail(detail, ex.getErrors());
        return ResponseEntity.badRequest().body(response);
    }
}
