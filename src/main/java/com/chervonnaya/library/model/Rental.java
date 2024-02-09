package com.chervonnaya.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental", schema = "lib_rest")
@Slf4j
public class Rental extends BaseEntity{
    @CreationTimestamp
    @Column(name = "rental_date", columnDefinition = "datetime")
    private LocalDateTime rentalDate;
    @ManyToOne
    @JoinColumn(name = "copy_id", referencedColumnName="id")
    @JsonIgnoreProperties("rentals")
    private Copy copy;
    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName="id")
    @JsonIgnoreProperties("rentals")
    private Reader reader;
    @Column(name = "return_date", columnDefinition = "datetime")
    private LocalDateTime returnDate;

}


