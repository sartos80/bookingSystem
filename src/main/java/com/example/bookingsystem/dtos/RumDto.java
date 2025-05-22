package com.example.bookingsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Behövs inte?
public class RumDto {
    private int id;
    private String type;
    private int capacity;

}
