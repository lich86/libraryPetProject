package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.AuthorDTO;

import java.util.Arrays;
import java.util.List;

public class ControllerTestData {
    public static final String GET_AUTHOR_URL = "/api/author/1";
    public static final String AUTHOR_URL = "/api/author";

    public static final String ADMIN_MAIL = "qwerty@qwerty.com";
    public static final List<String> BOOKS = Arrays.asList("Каштанка", "Толстый и тонкий");
    public static final AuthorDTO AUTHOR_DTO = new AuthorDTO("Антон", "Чехов", "Павлович", "Антоша Чехонте", BOOKS);



}
