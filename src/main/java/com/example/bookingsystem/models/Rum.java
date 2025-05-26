package com.example.bookingsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;


import java.util.ArrayList;
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

   @Min(value = 1, message = "Capacity must be at least 1")
   @Max(value = 4, message = "Capacity cannot be more than 4")
   private int capacity;

    // Endast enkelrum eller dubbelrum är tillåtna
    @Enumerated(EnumType.STRING)
    private RumTyp type;

    @Min(value = 0, message = "Max extra beds must be at least 0")
    @Max(value = 2, message = "Max extra beds cannot be more than 2")
    private int maxExtraBeds;

    @OneToMany(mappedBy = "rum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bokning> bokningar = new ArrayList<>();


}

