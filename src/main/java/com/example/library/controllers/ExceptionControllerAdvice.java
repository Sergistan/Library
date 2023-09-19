package com.example.library.controllers;

import com.example.library.exceptions.ErrorBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ErrorBook.class)
    public ResponseEntity<?> handlerErrorBook() {
        return new ResponseEntity<>("Error book!", HttpStatus.BAD_REQUEST);
    }


}
