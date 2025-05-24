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
public class DetaljerRumDto {
    private Long id;
    private String type;
    private int capacity;
    private int MaxExtraBeds;
    private List<BokningDto> bokningar;
}
