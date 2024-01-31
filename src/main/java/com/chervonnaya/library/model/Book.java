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
@Table(name = "book", schema = "lib_rest")
public class Book extends BaseEntity{
    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany
    @JoinColumn(name = "book_id", referencedColumnName="id")
    @JsonIgnoreProperties("book")
    private List<Copy> copies;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    @Column(name = "genres", length = 32)
    private List<Genre> genres;
}
