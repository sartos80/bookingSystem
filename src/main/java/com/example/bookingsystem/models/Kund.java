package com.example.bookingsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Please enter your name")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Za-zÅÄÖåäö]+$", message = "Name can only contain letters")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 50)
    @Email
    private String epost;
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[0-9\\-\\+\\s]{7,15}$", message = "Phonenumber can only contain numbers between the size 7-15")
    private String telefonnummer;

    @OneToMany(mappedBy = "kund", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Bokning> bokningar = new ArrayList<>(); // Initiera med tom lista

    // getter
    public List<Bokning> getBokningar() {
        return bokningar != null ? bokningar : new ArrayList<>(); // Null-check
    }
}
