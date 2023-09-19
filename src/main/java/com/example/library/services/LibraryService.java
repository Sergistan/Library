package com.example.library.services;

import com.example.library.exceptions.ErrorBook;
import com.example.library.models.Book;
import com.example.library.models.ReqBookAndUser;
import com.example.library.models.User;
import com.example.library.repositoties.BookRepository;
import com.example.library.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private Book bookById;
    private User userById;


    @Autowired
    public LibraryService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> all() {
        return bookRepository.findAll();
    }

    public boolean checkID(ReqBookAndUser reqBookAndUser) {
        bookById = bookRepository.findBookById(reqBookAndUser.getIdBook());
        userById = userRepository.findUserById(reqBookAndUser.getIdUser());
        return userById != null && bookById != null;
    }

    @Transactional
    public String getBook(ReqBookAndUser reqBookAndUser) {
        if (!checkID(reqBookAndUser) || bookById.getIsFree().equals(false)) {
            throw new ErrorBook();
        } else {
            bookById.setIsFree(false);

            User user = User.builder()
                    .id(reqBookAndUser.getIdUser())
                    .name(userById.getName())
                    .phoneNumber(userById.getPhoneNumber())
                    .books(Set.of(bookById)).
                    build();

            bookRepository.save(bookById);
            userRepository.save(user);
            System.out.println("");

            return user.getName() + " получил книгу:\"" + bookById.getName() + "\"";
        }

    }

    @Transactional
    public String putBook(ReqBookAndUser reqBookAndUser) {
        if (!checkID(reqBookAndUser) || bookById.getIsFree().equals(true)) {
            throw new ErrorBook();
        } else {
//            Book book = bookRepository.findBookById(reqBookAndUser.getIdBook());
//            User user = userRepository.findUserByIdAndBooksIn(reqBookAndUser.getIdUser(), Set.of(book));

            bookById.setIsFree(true);

            boolean b = userById.getBooks().removeIf(x -> x.getId().equals(reqBookAndUser.getIdBook()));

            userRepository.saveAndFlush(userById);

            System.out.println(b);

            return userById.getName() + " вернул книгу:\"" + bookById.getName() + "\"";
        }
    }
}
