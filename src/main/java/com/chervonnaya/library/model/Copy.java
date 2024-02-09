package com.chervonnaya.library.model;

import com.chervonnaya.library.model.enums.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "copies", schema = "lib_rest")
public class Copy extends BaseEntity{
    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(64)")
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language;
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double price;
    @Column(name = "publishing_house", columnDefinition = "VARCHAR(128)")
    private String publishingHouse;
    @Column(name = "publishing_year", columnDefinition = "YEAR")
    private Year publishingYear;
    @Column(name="translator", columnDefinition = "VARCHAR(64)")
    private String translator;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @OneToMany
    @JoinColumn(name = "copy_id")
    @JsonIgnoreProperties("copy")
    private Set<Rental> rentals;

}
