package com.chervonnaya.library.service;

import com.chervonnaya.library.dto.RentalDTO;
import com.chervonnaya.library.model.Rental;

import java.util.Set;


public interface RentalService extends CrudService<Rental, RentalDTO>{
    Set<Rental> saveRentals(RentalDTO rentalDTO);
    Rental returnCopy(Long id);
}
