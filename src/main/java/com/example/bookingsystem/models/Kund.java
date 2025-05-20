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
