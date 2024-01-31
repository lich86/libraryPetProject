package com.chervonnaya.library.repository;

import com.chervonnaya.library.model.Copy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends IRepository<Copy>{
    @Query("SELECT c.id, c.book.title, c.rentalDate, c.returnDate FROM Copy c JOIN c.readers r WHERE r.id = :readerId")
    Page<Copy> getAllByReader(@Param("readerId") long readerId, Pageable pageable);
}
