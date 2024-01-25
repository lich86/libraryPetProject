package com.chervonnaya.library.dto;

import com.chervonnaya.library.model.Genre;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class BookDTO extends BaseDTO{
    private String title;
    private String description;
    private List<Long> authorIds;
    private List<Genre> genres;
}
