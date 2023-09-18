package com.example.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserGetBook {

    private Long idBook;

    private Long idUser;
}
