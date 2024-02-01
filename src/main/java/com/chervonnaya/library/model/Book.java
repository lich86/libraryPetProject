package com.chervonnaya.library.model;

import com.chervonnaya.library.model.enums.Genre;
import com.chervonnaya.library.model.enums.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book", schema = "lib_rest")
public class Book extends BaseEntity{
    @Column(name = "original_title", nullable = false, columnDefinition = "VARCHAR(64)")
    private String originalTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "original_language")
    private Language originalLanguage;

    @Column(name = "description", columnDefinition = "TEXT(2000)")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName="id")
    @JsonIgnoreProperties("book")
    private Set<Copy> copies;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("books")
    private Set<Author> authors;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    @Column(name = "genres", length = 32)
    private Set<Genre> genres;
}
