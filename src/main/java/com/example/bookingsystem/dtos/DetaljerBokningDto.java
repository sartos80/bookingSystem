package com.example.bookingsystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetaljerBokningDto {
    private Long id;
    //Kontrollerar att datumen inte är null.
    @NotNull(message = "Start date must not be null")
    private LocalDate date;
    @NotNull(message = "End date must not be null")
    private LocalDate endDate;
    private int extraBeds;
    @NotNull(message = "bokning must have a kund")
    private KundDto kund;
    @NotNull(message = "bokning must have a rum")
    private RumDto rum;
}
