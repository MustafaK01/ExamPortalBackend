package com.examportal.examportalbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(x->{
            String fieldName = ((FieldError)x).getField();
            String errorMsg = x.getDefaultMessage();
            errors.put(fieldName,errorMsg);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                this.adjustMessages(e));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                this.adjustMessages(e));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> invalidTokenException(InvalidTokenException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                this.adjustMessages(e));
    }

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<?> invalidParameterException(InvalidParamException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                this.adjustMessages(e));
    }

    private Map<String, String> adjustMessages(RuntimeException exception){
        Map<String,String> errors = new HashMap<>();
        errors.put("msg",exception.getMessage());
        return errors;
    }

}
