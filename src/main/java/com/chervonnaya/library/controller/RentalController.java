package com.chervonnaya.library.controller;

import com.chervonnaya.library.dto.RentalDTO;
import com.chervonnaya.library.model.Rental;
import com.chervonnaya.library.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
    private final RentalService rentalService;
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Rental> getRentals(Pageable pageable) {
        return rentalService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Rental getRental(@PathVariable(name = "id") Long id) {
        return rentalService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Set<Rental> addRentals(@Valid @RequestBody RentalDTO rentalDTO) {
        return rentalService.saveRentals(rentalDTO);
    }

    @RequestMapping(value = "/return-copy/{id}", method = RequestMethod.PATCH)
    public Rental returnCopy(@PathVariable(name = "id") Long copyId) {
        return rentalService.returnCopy(copyId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteRental(@PathVariable(name = "id") Long id) {
        rentalService.delete(id);
    }
}
