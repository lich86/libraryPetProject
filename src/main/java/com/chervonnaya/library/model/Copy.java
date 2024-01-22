package com.chervonnaya.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "copy", schema = "lib_rest")
public class Copy extends BaseEntity{
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "rental_date", columnDefinition = "datetime")
    LocalDateTime rentalDate;
    @Column(name = "return_date", columnDefinition = "datetime")
    LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName="id")
    Reader reader;



}
