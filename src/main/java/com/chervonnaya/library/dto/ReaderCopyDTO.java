package com.chervonnaya.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReaderCopyDTO extends BaseDTO{
    private List<Long> copyIds;
}
