package com.example.bookingsystem.services;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.models.Kund;

public interface KundService {
    public KundDto kundToKundDto(Kund k);
    public DetaljerKundDto kundToDetaljerKundDto(Kund k);
}

