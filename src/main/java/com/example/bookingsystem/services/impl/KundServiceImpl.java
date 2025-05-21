package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.models.Bokning;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.KundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundServiceImpl implements KundService {
    private  KundRepo kundRepo;
    private  final BokningService bokningService;
    @Override
    public List<DetaljerKundDto> getAllKunder() {
        return kundRepo.findAll().stream().map(k -> kundToDetaljerKundDto(k)).toList();
    }
    @Override
    public KundDto kundToKundDto(Kund k) {
        return KundDto.builder().id(k.getId()).name(k.getName()).build();
    }

    @Override
    public DetaljerKundDto kundToDetaljerKundDto(Kund k) {
        return DetaljerKundDto.builder().id(k.getId()).name(k.getName()).epost(k.getEpost()).telefonnummer(k.getTelefonnummer())
                .bokningar(k.getBokningar().stream().map(bokning -> BokningServiceImpl.bokningToBokningDto(bokning)).toList()).build();

    }


}



