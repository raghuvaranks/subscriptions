package com.fs.subscriptions.subscriptions.exception;

import com.fs.subscriptions.subscriptions.payload.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exp){
        log.error("Handling Resouce Not Found Error 400");
        return new ResponseEntity<>(new ErrorDTO(exp.getMessage(),exp.getFieldName(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        log.error("Handling Invalid Input");
        return new ResponseEntity<>(new ErrorDTO(exp.getBindingResult().getFieldError().getDefaultMessage(),exp.getBindingResult().getFieldError().getField(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

}
