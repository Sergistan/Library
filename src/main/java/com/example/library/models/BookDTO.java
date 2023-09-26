package com.example.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String name;
    private String author;
    private Integer dateOfPublication;
    private Boolean isFree;
    private LocalDateTime timeGetBook;
}
