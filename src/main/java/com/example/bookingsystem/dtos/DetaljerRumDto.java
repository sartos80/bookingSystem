package com.example.bookingsystem.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetaljerRumDto {
    private Long id;
    @NotEmpty
    private String type;
    @NotEmpty
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 4, message = "Capacity cannot be more than 4")
    private int capacity;
    @NotEmpty
    @Min(value = 0, message = "Max extra beds must be at least 0")
    @Max(value = 2, message = "Max extra beds cannot be more than 2")
    private int MaxExtraBeds;
    private List<BokningDto> bokningar;
}
