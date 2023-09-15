package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "phone_number")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name="user_book",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    @JsonIgnore
    private List<Book> books;

    public void addBook(Book book) {
        this.books.add(book);
        book.getUsers().add(this);
    }

    public void removeBook(long bookId) {
        Book book = this.books.stream().filter(t -> t.getId() == bookId).findFirst().orElse(null);
        if (book != null) {
            this.books.remove(book);
            book.getUsers().remove(this);
        }
    }


}
