package com.example.bookingsystem.services;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.models.Kund;

import java.util.List;

public interface KundService {
    public KundDto kundToKundDto(Kund k);
    public DetaljerKundDto kundToDetaljerKundDto(Kund k);
    public Kund DetaljerKundDtoToKund(DetaljerKundDto k);
    // metod för att hämta all kunder
    public List<DetaljerKundDto> getAllKunder();
    // metod för skapa ny kund
    public String addKund(DetaljerKundDto kund);
}

