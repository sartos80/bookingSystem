package com.example.bookingsystem.services;

import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.dtos.DetaljerRumDto;
import com.example.bookingsystem.models.Rum;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RumService {

    public List<DetaljerRumDto> findEmptyRum(LocalDate date, LocalDate endDate);
    public DetaljerRumDto rumToDetaljerRumDTO(Rum rum);
    public List<DetaljerRumDto> findEmptyRumIgnoreCurrent(LocalDate date, LocalDate endDate, DetaljerBokningDto bokning);

}
