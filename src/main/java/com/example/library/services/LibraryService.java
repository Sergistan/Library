package com.example.library.services;

import com.example.library.exceptions.ErrorGetBook;
import com.example.library.exceptions.ErrorPutBook;
import com.example.library.models.*;
import com.example.library.repositoties.BookRepository;
import com.example.library.repositoties.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final Notification notification;
    private Book bookById;
    private User userById;


    @Autowired
    public LibraryService(BookRepository bookRepository, UserRepository userRepository, Notification notification) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.notification = notification;
    }

    public List<BookDTO> allBooks() {
        return bookRepository.findAll().stream().map(LibraryService::convertToDTO).collect(Collectors.toList());
    }

    private static BookDTO convertToDTO(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(book, BookDTO.class);
    }

    public boolean checkID(ReqBookAndUser reqBookAndUser) {
        bookById = bookRepository.findBookById(reqBookAndUser.getIdBook());
        userById = userRepository.findUserById(reqBookAndUser.getIdUser());
        return userById != null && bookById != null;
    }

    @Transactional
    public String getBook(ReqBookAndUser reqBookAndUser) {
        if (!checkID(reqBookAndUser) || bookById.getIsFree().equals(false)) {
            throw new ErrorGetBook();
        } else {

            bookById.setIsFree(false);
            userById.addBook(bookById);
            bookById.setTimeGetBook(LocalDateTime.now());
            String notificationUser = notification.notificationUser(userById);

            return userById.getName() + " получил книгу:\"" + bookById.getName() + "\""
                    + "\n" + notificationUser;
        }

    }

    @Transactional
    public String putBook(ReqBookAndUser reqBookAndUser) {
        if (!checkID(reqBookAndUser) || bookById.getIsFree().equals(true)) {
            throw new ErrorPutBook();
        } else {

            bookById.setIsFree(true);
            userById.removeBook(bookById);
            String notificationUser = notification.notificationUser(userById);
            bookById.setTimeGetBook(null);

            return userById.getName() + " вернул книгу:\"" + bookById.getName() + "\""
                    + "\n" + notificationUser;
        }
    }

}
