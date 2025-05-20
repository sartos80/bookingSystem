package com.example.bookingsystem.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetaljerKundDto {
    private Long id;
    private String name;
    private String epost;
    private String telefonnummer;
    private BokningDto bokningar; // List of minimal booking DTOs
}

