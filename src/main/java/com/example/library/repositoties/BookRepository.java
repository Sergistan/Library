package com.example.library.repositoties;


import com.example.library.models.Book;
import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByUsersId (long userId);

    Book findBookById (long id);
}
