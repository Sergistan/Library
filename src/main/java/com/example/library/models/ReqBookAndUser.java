package com.example.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqBookAndUser {

    private Long idUser;
    private Long idBook;
}
