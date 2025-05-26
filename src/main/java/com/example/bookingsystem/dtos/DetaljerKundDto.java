package com.example.bookingsystem.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetaljerKundDto {
    private Long id;
    @NotEmpty(message = "Please enter your name")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-ZazÅÄÖåäö]+$", message = "Name can only contain letters")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 50)
    @Email
    private String epost;
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[0-9\\-\\+\\s]{7,15}$", message = "Phonenumber can only contain numbers between the size 7-15")
    private String telefonnummer;
    private List<BokningDto> bokningar; // List of minimal booking DTOs
}

