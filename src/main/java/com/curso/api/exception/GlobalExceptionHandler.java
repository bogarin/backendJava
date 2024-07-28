package com.curso.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.api.dto.HandleErrorMessageDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HandleErrorMessageDTO> handleResourceNotFoundException(HttpServletRequest request,
            ObjectNotFoundException ex) {
        HandleErrorMessageDTO message = HandleErrorMessageDTO.builder()
                .backendMessage(ex.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("no existe ese valor")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HandleErrorMessageDTO> handleMethodArgumentNotValidException(HttpServletRequest request,
            MethodArgumentNotValidException ex) {
        HandleErrorMessageDTO message = HandleErrorMessageDTO.builder()
                .backendMessage(ex.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message("no son validos los valores")
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<HandleErrorMessageDTO> handleMethodArgumentNotValidException(HttpServletRequest request,
    InvalidPasswordException ex) {
        HandleErrorMessageDTO message = HandleErrorMessageDTO.builder()
                .backendMessage(ex.getLocalizedMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

}
