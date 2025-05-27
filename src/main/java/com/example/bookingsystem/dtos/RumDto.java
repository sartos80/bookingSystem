package com.example.bookingsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RumDto {
    private Long id;
    private String type;
    private int capacity;
    private int MaxExtraBeds;

}
