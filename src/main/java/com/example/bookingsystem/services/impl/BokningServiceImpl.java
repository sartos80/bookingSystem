package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.*;
import com.example.bookingsystem.models.Bokning;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.models.Rum;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.repo.RumRepo;
import com.example.bookingsystem.services.BokningService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BokningServiceImpl implements BokningService
{
    private final KundRepo kundRepo;
    private final RumRepo rumRepo;
    private final BokningRepo bokningRepo;

    public BokningServiceImpl(KundRepo kundRepo, RumRepo rumRepo, BokningRepo bokningRepo)
    {
        this.kundRepo = kundRepo;
        this.rumRepo = rumRepo;
        this.bokningRepo = bokningRepo;
    }

    @Override
    public List<DetaljerBokningDto> getAllBokningar()
    {
        return bokningRepo.findAll().stream().map(b-> bokningToDetaljerBokningDto(b)).toList();
    }

    @Override
    public DetaljerBokningDto getBokningById(Long id) {
        return bokningRepo.findById(id)
                .map(bokning -> bokningToDetaljerBokningDto(bokning))
                .orElse(null);
    }

    @Override
    public DetaljerBokningDto addBokning(DetaljerBokningDto bokning)
    {
        Kund kund = kundRepo.findById(bokning.getKund().getId()).get();
        Rum rum = rumRepo.findById(bokning.getRum().getId()).get();
/*
        if (bokningRepo.existsByRumAndDate(rum, bokning.getDate())) {
            return DetaljerBokningDto.builder()
                    .id(null)
                    .date(bokning.getDate())
                    .endDate(bokning.getEndDate())
                    .kund(bokning.getKund())
                    .rum(bokning.getRum())
                    .build();
        }
        //TODO kommentera bort för att den krocka med att uppdatera endDate.
 */

        Bokning sparadBokning = bokningRepo.save(detaljerBokningDtoToBokning(bokning, kund, rum));
        return bokningToDetaljerBokningDto(sparadBokning);
    }

    @Override
    public boolean deleteBokning(Long bokningsId) {
        if (!bokningRepo.existsById(bokningsId)) {
            return false;  // Finns inte, kan inte ta bort
        }
        bokningRepo.deleteById(bokningsId);
        return true;  // Borttagning lyckades
    }

    @Override
    public DetaljerBokningDto updateBokning(DetaljerBokningDto bokningDto) {
        Bokning bokning = bokningRepo.findById(bokningDto.getId()).orElse(null);
        if (bokning == null) return null;

        Kund kund = kundRepo.findById(bokningDto.getKund().getId()).orElse(null);
        Rum rum = rumRepo.findById(bokningDto.getRum().getId()).orElse(null);
        if (kund == null || rum == null) return null;

        bokning.setDate(bokningDto.getDate());
        bokning.setKund(kund);
        bokning.setRum(rum);

        return bokningToDetaljerBokningDto(bokningRepo.save(bokning));
    }

    @Override
    public BokningDto bokningToBokningDto(Bokning bokning)
    {
        return BokningDto.builder().id(bokning.getId()).date(bokning.getDate()).rum(bokning.getRum().getId()).build();
    }

    @Override
    public DetaljerBokningDto bokningToDetaljerBokningDto(Bokning bokning)
    {
        return DetaljerBokningDto.builder()
                .id(bokning.getId())
                .date(bokning.getDate())
                .endDate(bokning.getEndDate())
                .kund(new KundDto(bokning.getKund().getId(), bokning.getKund().getName()))
                .rum(new RumDto(bokning.getRum().getId(), bokning.getRum().getType(), bokning.getRum().getCapacity()))
                .build();
    }

    @Override
    public Bokning detaljerBokningDtoToBokning(DetaljerBokningDto detaljerBokningDto, Kund k, Rum r)
    {
        return Bokning.builder()
                .id(detaljerBokningDto.getId())
                .date(detaljerBokningDto.getDate())
                .endDate(detaljerBokningDto.getEndDate())
                .kund(k)
                .rum(r)
                .build();
    }

    @Override
    public Bokning bokningDtoToBokning(BokningDto bokningDto)
    {
        return Bokning.builder().id(bokningDto.getId()).date(bokningDto.getDate()).build();
    }
}
