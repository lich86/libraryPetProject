package com.chervonnaya.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 32, nullable = false)
    private String middleName;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reader_id")
    @JsonIgnoreProperties("reader")
    private List<Rental> rentals;
}
