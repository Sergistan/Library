package com.example.library.repositoties;

import com.example.library.models.Book;
import com.example.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById (long id);

    User findUserByIdAndBooksIn (long id, Set<Book> books);
}
