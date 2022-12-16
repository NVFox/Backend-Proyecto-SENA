package com.tienda.backend.filter;

import com.tienda.backend.dto.error.ErrorResponseDto;
import com.tienda.backend.exceptions.BadCredentialsException;
import com.tienda.backend.exceptions.NotEnoughMoneyException;
import com.tienda.backend.exceptions.NotEnoughQuantityException;
import com.tienda.backend.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionFilter {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> exceptionHandler(Exception exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage() == null
                ? "Un error inesperado ha sucedido"
                : exception.getMessage();

        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), message);

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> runtimeExceptionHandler(RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage() == null
                ? "Un error inesperado ha sucedido"
                : exception.getMessage();

        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), message);

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> badCredentialsExceptionHandler(BadCredentialsException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorResponseDto> notEnoughMoneyExceptionHandler(NotEnoughMoneyException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(NotEnoughQuantityException.class)
    public ResponseEntity<ErrorResponseDto> notEnoughQuantityExceptionHandler(NotEnoughQuantityException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundExceptionHandler(NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDto responseDto = new ErrorResponseDto(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(responseDto);
    }
}
