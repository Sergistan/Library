package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.models.BookDTO;
import com.example.library.services.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping("/all")
    public List<BookDTO> books() {
        return libraryService.all().stream().map(BookController::convertToDTO).collect(Collectors.toList());
    }

    private static BookDTO convertToDTO(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(book, BookDTO.class);
    }

    @GetMapping("user/{userId}/book/{bookId}")
    public ResponseEntity<?> getBook (@PathVariable Long userId, @PathVariable Long bookId){
        libraryService.getBook(userId, bookId);
        return new ResponseEntity<>(userId + " получил книгу " + bookId, HttpStatus.OK);
    }
}
