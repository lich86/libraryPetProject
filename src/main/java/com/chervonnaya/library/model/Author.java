package com.chervonnaya.library.model;

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
@Table(name = "author", schema = "lib_rest")
public class Author extends BaseEntity{
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(32)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(32)")
    private String lastName;

    @Column(name = "middle_name", columnDefinition = "VARCHAR(32)")
    private String middleName;

    @Column(name = "pen_name", columnDefinition = "VARCHAR(64)")
    private String penName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn (name="author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id")
    )
    @JsonIgnoreProperties("authors")
    private Set<Book> books;
}
