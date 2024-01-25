package com.chervonnaya.library.dto;

import lombok.*;


@Getter
@Setter
public class ReaderDTO extends BaseDTO{
    private String firstName;
    private String lastName;
    private String middleName;
    private Long copyId;
}
