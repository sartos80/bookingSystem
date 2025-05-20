package com.example.bookingsystem.dtos;

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
    private LocalDate date;
    private KundDto kund;
    private RumDto rum;
}
