package com.chervonnaya.library.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class AuthorDTO extends BaseDTO{
    private String firstName;
    private String lastName;
    private String middleName;
    private List<String> bookTitles;
}
