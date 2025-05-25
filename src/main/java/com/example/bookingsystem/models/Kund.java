package com.example.bookingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
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

    @OneToMany(mappedBy = "kund", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bokning> bokningar = new ArrayList<>(); // Initiera med tom lista

    // getter
    public List<Bokning> getBokningar() {
        return bokningar != null ? bokningar : new ArrayList<>(); // Null-check
    }
}
