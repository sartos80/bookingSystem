package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.*;
import com.example.bookingsystem.models.Rum;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.repo.RumRepo;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.RumService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RumServiceImpl implements RumService {

    private final KundRepo kundRepo;
    private final RumRepo rumRepo;
    private final BokningRepo bokningRepo;
    private  final BokningService bokningService;

    public RumServiceImpl(KundRepo kundRepo, RumRepo rumRepo, BokningRepo bokningRepo, BokningService bokningService)
    {
        this.bokningService = bokningService;
        this.kundRepo = kundRepo;
        this.rumRepo = rumRepo;
        this.bokningRepo = bokningRepo;
    }

    @Override
    public  List<DetaljerRumDto> findEmptyRumIgnoreCurrent(LocalDate date, LocalDate endDate,
                                                          int capacity,DetaljerBokningDto bokning){
        if(!date.isBefore(endDate.plusDays(1))){

            return null;

        } else {
            List<LocalDate> searchDates = date.datesUntil(endDate.plusDays(1)).toList();

            return rumRepo.findAll().stream().map(this::rumToDetaljerRumDTO)
                    .filter(room -> room.getCapacity() >= capacity).toList().stream()
                    .filter(r -> Collections.disjoint(r.getBokningar().stream()
                            .filter(b -> !Objects.equals(b.getId(), bokning.getId()))
                            .flatMap(b -> b.getDate().datesUntil(b.getEndDate().plusDays(1)))
                            .toList(), searchDates)).collect(Collectors.toList());
        }
    }

    @Override
    public List<DetaljerRumDto> findEmptyRum(LocalDate date, LocalDate endDate, int capacity) {

        if(!date.isBefore(endDate.plusDays(1))){

            return null;

        } else {

            List<LocalDate> searchDates = date.datesUntil(endDate.plusDays(1)).toList();

            return rumRepo.findAll().stream().map(this::rumToDetaljerRumDTO)
                    .filter(room -> room.getCapacity() >= capacity).toList().stream()
                    .filter(r -> Collections.disjoint(r.getBokningar().stream()
                            .flatMap(b -> b.getDate().datesUntil(b.getEndDate().plusDays(1)))
                            .toList(), searchDates)).collect(Collectors.toList());
        }
    }

    @Override
    public int roomMaxCapacity(){
        return rumRepo.findAll().stream().mapToInt(Rum::getCapacity).max().orElse(0);
    }

    @Override
    public DetaljerRumDto rumToDetaljerRumDTO(Rum rum) {

        return DetaljerRumDto.builder()
                .id(rum.getId())
                .type(rum.getType())
                .capacity(rum.getCapacity())
                .MaxExtraBeds(rum.getMaxExtraBeds())
                .bokningar(rum.getBokningar().stream().map(bokningService::bokningToBokningDto).toList())
                .build();
    }


}
