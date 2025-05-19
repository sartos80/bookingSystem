package com.example.bookingsystem.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class Kund {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String epost;
    private String telefonnummer;

    @OneToMany(mappedBy = "kund", cascade = CascadeType.ALL)
    private List<Bokning> bokningar;
}
