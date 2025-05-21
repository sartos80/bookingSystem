package com.example.bookingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rum {
    @Id
    @GeneratedValue
    private Long id;
    private String typ;
    private int kapacitet;

    @OneToMany(mappedBy = "rum", cascade = CascadeType.ALL)
    private List<Bokning> bokningar;
}