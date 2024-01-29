package com.chervonnaya.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "copies", schema = "lib_rest")
public class Copy extends BaseEntity{
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "rental_date", columnDefinition = "datetime")
    LocalDateTime rentalDate;
    @Column(name = "return_date", columnDefinition = "datetime")
    LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn(name = "book_id", columnDefinition = "id")
    private Book book;
    @ManyToMany
    @JoinTable(name = "copy_reader",
            joinColumns = @JoinColumn (name="copy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="reader_id", referencedColumnName="id")
    )
    @JsonIgnoreProperties("copies")
    List<Reader> readers;

}
