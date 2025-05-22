package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.BokningDto;
import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.dtos.KundDto;
import com.example.bookingsystem.dtos.RumDto;
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
    public String addBokning(DetaljerBokningDto bokning)
    {
        Kund kund = kundRepo.findById(bokning.getKund().getId()).get();
        Rum rum = rumRepo.findById(bokning.getRum().getId()).get();

        if (bokningRepo.redanBokat(rum, bokning.getDate())){
            return "Redan bokat, testa ett annat rum eller datum";
        }
        bokningRepo.save(detaljerBokningDtoToBokning(bokning, kund, rum));
        return "Bokning tillagd";
    }

    @Override
    public String deleteBokning(Long bokningsId)
    {
        if(!bokningRepo.existsById(bokningsId)){
            return "Ingen bokning hittades med id  " + bokningsId;
        }
        bokningRepo.deleteById(bokningsId);
        return "Bokning raderad";
    }

    @Override
    public String updateBokning(DetaljerBokningDto bokning)
    {

        return "Bokning uppdaterad";
    }

    @Override
    public BokningDto bokningToBokningDto(Bokning bokning)
    {
        return BokningDto.builder().id(bokning.getId()).date(bokning.getDate()).build();
    }

    @Override
    public DetaljerBokningDto bokningToDetaljerBokningDto(Bokning bokning)
    {
        return DetaljerBokningDto.builder()
                .id(bokning.getId())
                .date(bokning.getDate())
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
