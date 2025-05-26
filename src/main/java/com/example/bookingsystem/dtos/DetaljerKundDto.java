package com.example.bookingsystem.dtos;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

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
    private List<BokningDto> bokningar; // List of minimal booking DTOs
}

