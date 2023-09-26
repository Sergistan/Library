package com.example.library.controllers;

import com.example.library.models.BookDTO;
import com.example.library.models.ReqBookAndUser;
import com.example.library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping("/all")
    public List<BookDTO> books() {
        return libraryService.allBooks();
    }

    @PostMapping("/get_book")
    public ResponseEntity<?> getBook (@RequestBody ReqBookAndUser reqBookAndUser){
        String info = libraryService.getBook(reqBookAndUser);
        return new ResponseEntity<>(info, HttpStatus.CREATED);
    }


    @PostMapping("/put_book")
    public ResponseEntity<?> putBook (@RequestBody ReqBookAndUser reqBookAndUser){
        String info = libraryService.putBook(reqBookAndUser);
        return new ResponseEntity<>(info, HttpStatus.CREATED);
    }


}
