package com.example.library.services;

import com.example.library.exceptions.BookIsNotFree;
import com.example.library.models.Book;
import com.example.library.models.User;
import com.example.library.repositoties.BookRepository;
import com.example.library.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public void getBook(Long userId, Long bookId) {
        Book book = bookRepository.findBookById(bookId);
        if (book == null || (book.getIsFree().equals(false))) {
            throw new BookIsNotFree();
        } else {
            book.setIsFree(false);
            User userById = userRepository.findUserById(userId);
            User user = new User(); // до этого искал user через репозиторий
            user.setId(userId);
            user.setName(userById.getName());
            user.setPhoneNumber(userById.getPhoneNumber());
            user.setBooks(List.of(book));
            bookRepository.save(book);
            userRepository.save(user);
        }
    }
}
