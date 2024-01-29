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
@Table(name = "author", schema = "lib_rest")
public class Author extends BaseEntity{
    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 32, nullable = false)
    private String middleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn (name="author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id")
    )
    @JsonIgnoreProperties("authors")
    private List<Book> books;
}
