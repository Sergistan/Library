package com.example.library.services;

import com.example.library.exceptions.BookIsNotFree;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.models.UserGetBook;
import com.example.library.repositoties.BookRepository;
import com.example.library.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> all() {
        return bookRepository.findAll();
    }


    public void getBook(UserGetBook userGetBook) {
        Book book = bookRepository.findBookById(userGetBook.getIdBook());
        if (book == null || (book.getIsFree().equals(false))) {
            throw new BookIsNotFree();
        } else {
            book.setIsFree(false);
            User userById = userRepository.findUserById(userGetBook.getIdUser());

            User user = new User();
            user.setId(userGetBook.getIdUser());
            user.setName(userById.getName());
            user.setPhoneNumber(userById.getPhoneNumber());
            user.setBooks(Set.of(book));
            bookRepository.save(book);
            userRepository.save(user);
        }
    }
}
