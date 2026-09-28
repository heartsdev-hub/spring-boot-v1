package com.daiquiriclub.app.springbootv1.exception;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResult<Void>> handleExceptionHandler(Exception exception){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResult<>(
//                false,"Internal Server Error",null
//        ));
//    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResult<Void>> handleBadRequest(BadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResult<>(false, exception.getMessage(),null)
        );
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleResourceNotFound(ResourceNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResult<>(
                false, exception.getMessage(), null
        ));
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResult<Void>> handleConflictException(ConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResult<>(
                false, exception.getMessage(), null
        ));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Map<String,String>>> handleMethodArgumentNotValidException( MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                e -> errors.put(e.getField(),e.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResult<>(false,"Errors validation",errors));
    }
}
