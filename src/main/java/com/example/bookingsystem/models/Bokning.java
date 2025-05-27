package com.example.bookingsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bokning {

    @Id
    @GeneratedValue
    private Long id;
    //Kontrollerar att datumen inte är null.
    @NotNull(message = "Start date must not be null")
    private LocalDate date;
    @NotNull(message = "End date must not be null")
    private LocalDate endDate;
    @Min(value = 0, message = "Extra beds must be at least 0")
    private int extraBeds;

    @ManyToOne
    @JoinColumn(name="kund_Id")
    @NotNull(message = "bokning must have a kund")
    private Kund kund;

    @ManyToOne
    @JoinColumn(name="rum_id")
    @NotNull(message = "bokning must have a rum")
    private Rum rum;
}
