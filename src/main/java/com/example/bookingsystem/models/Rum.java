package com.example.bookingsystem.models;

import jakarta.persistence.*;

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
    private int capacity;
    private String type;
    private int maxExtraBeds;

    @OneToMany(mappedBy = "rum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bokning> bokningar = new ArrayList<>();


}

