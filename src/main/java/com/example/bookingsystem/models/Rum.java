package com.example.bookingsystem.models;

import jakarta.persistence.*;
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

    //1 3 eller 4
    private int capacity;
    private String type;
    private int MaxExtraBeds;

    @OneToMany(mappedBy = "rum", cascade = CascadeType.ALL)
    private List<Bokning> bokningar;
}
