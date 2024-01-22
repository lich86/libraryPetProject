package com.chervonnaya.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reader", schema = "lib_rest")
public class Reader extends BaseEntity{
    @OneToMany(mappedBy = "reader")
    private List<Copy> copies;
}
