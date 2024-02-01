package com.chervonnaya.library.repository;

import com.chervonnaya.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends IRepository<Book> {
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a LEFT JOIN FETCH b.copies WHERE a.id = :authorId")
    Page<Book> getAllByAuthor(@Param("authorId") long authorId, Pageable pageable);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a LEFT JOIN FETCH b.copies WHERE a.id = :authorId")
    Set<Book> getAllByAuthor(@Param("authorId") long authorId);
}
