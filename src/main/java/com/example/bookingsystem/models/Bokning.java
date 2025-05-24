package com.example.bookingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate endDate;
    private int extraBeds;

    @ManyToOne
    @JoinColumn(name="kund_Id")
    private Kund kund;

    @ManyToOne
    @JoinColumn(name="rum_id")
    private Rum rum;
}
