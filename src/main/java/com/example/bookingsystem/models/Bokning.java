package com.example.bookingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bokning {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="kundId", referencedColumnName = "id")
    private Kund kund;

    @ManyToOne
    @JoinColumn(name="rumId", referencedColumnName = "id")
    private Rum rum;
}
