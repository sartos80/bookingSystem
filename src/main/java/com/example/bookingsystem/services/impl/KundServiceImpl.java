package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.services.KundService;
import org.springframework.stereotype.Service;

@Service
public class KundServiceImpl implements KundService {
    @Override
    public KundDto kundToKundDto(Kund k) {
        return KundDto.builder().id(k.getId()).name(k.getName()).build();
    }

    @Override
    public DetaljerKundDto kundToDetaljerKundDto(Kund k) {
        return null;
    }
}
