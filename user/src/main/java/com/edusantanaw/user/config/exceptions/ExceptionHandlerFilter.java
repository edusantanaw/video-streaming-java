package com.edusantanaw.user.config.exceptions;

import com.edusantanaw.user.config.exceptions.dto.ExceptionResponseDTO;
import com.edusantanaw.user.domain.exceptions.DomainValidation;
import com.edusantanaw.user.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice()
public class ExceptionHandlerFilter {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> notFoundException(NotFoundException error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDTO(error.getMessage()));
    }

    @ExceptionHandler(DomainValidation.class)
    private ResponseEntity<ExceptionResponseDTO> domainValidationException(DomainValidation exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<MethodArgumentNotValidException> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler
    public ResponseEntity<String> serverException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
