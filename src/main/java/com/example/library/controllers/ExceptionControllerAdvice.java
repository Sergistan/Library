package com.example.library.controllers;

import com.example.library.exceptions.ErrorGetBook;
import com.example.library.exceptions.ErrorPutBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ErrorGetBook.class)
    public ResponseEntity<?> handlerErrorGetBook() {
        return new ResponseEntity<>("Error get book!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorPutBook.class)
    public ResponseEntity<?> handlerErrorPutBook() {
        return new ResponseEntity<>("Error put book!", HttpStatus.NOT_FOUND);
    }

}
