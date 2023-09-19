package com.example.library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "author")
    private String author;

    @Column (name = "date_of_publication")
    private Integer dateOfPublication;

    @Column (name = "is_free")
    private Boolean isFree;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

}
