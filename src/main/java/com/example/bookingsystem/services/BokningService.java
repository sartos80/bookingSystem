package com.example.bookingsystem.services;

import com.example.bookingsystem.dtos.BokningDto;
import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.models.Bokning;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.models.Rum;

import java.util.List;

public interface BokningService {
    public BokningDto bokningToBokningDto(Bokning bokning);

    public DetaljerBokningDto bokningToDetaljerBokningDto(Bokning bokning);

    public Bokning detaljerBokningDtoToBokning(DetaljerBokningDto detaljerBokningDto, Kund kund, Rum rum);

    public Bokning bokningDtoToBokning(BokningDto bokningDto);

    public List<DetaljerBokningDto> getAllBokningar();

    public DetaljerBokningDto addBokning(DetaljerBokningDto bokning);

    public boolean deleteBokning(Long bokningsId);

    public DetaljerBokningDto updateBokning(DetaljerBokningDto bokning);

    public DetaljerBokningDto getBokningById(Long id);
}
