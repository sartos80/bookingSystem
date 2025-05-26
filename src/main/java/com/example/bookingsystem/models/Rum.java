package com.example.bookingsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rum {
    @Id
    @GeneratedValue
    private long id;

    // Antal ordinarie sängplatser : 1 till 4
    @Min(value = 1, message = "capacity must be at least 1")
    @Max(value = 4, message = "capacity cannot be more than  2")
    private int capacity;
    // typ av rum (Enkelrum eller Dubbelrum)
    @NotBlank(message = "Romm type must not be empty")
    private String type;
    // Antal tillåtna extra sängplatser : 0 till 1
    @Min(value = 0, message = "MaxExtraBeds must be at least 0")
    @Max(value = 1, message = "MaxExtraBeds cannot be more than  1")
    private int MaxExtraBeds;

    @OneToMany(mappedBy = "rum", cascade = CascadeType.ALL)
    private List<Bokning> bokningar;
}
