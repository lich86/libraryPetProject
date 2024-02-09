package com.chervonnaya.library.repository;

import com.chervonnaya.library.model.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends IRepository<Rental>{

    @Query("FROM Rental r WHERE r.copy.id = :copyId and r.returnDate IS NULL")
    Rental findByCopyId(@Param("copyId")Long copyId);
}
