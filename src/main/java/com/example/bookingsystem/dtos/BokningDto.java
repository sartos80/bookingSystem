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
public class BokningDto
{
    private Long id;
    private Long rum;
    private LocalDate date;
    private LocalDate endDate;
    private int extraBeds;
    private String type;

}
