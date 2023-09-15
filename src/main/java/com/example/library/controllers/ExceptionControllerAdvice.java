package com.example.library.controllers;

import com.example.library.exceptions.BookIsNotFree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BookIsNotFree.class)
    public ResponseEntity<?> handlerBookIsNotFree() {
        return new ResponseEntity<>("Book is not free!", HttpStatus.BAD_REQUEST);
    }


}
