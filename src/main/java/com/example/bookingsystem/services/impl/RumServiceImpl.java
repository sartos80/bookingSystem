package com.example.bookingsystem.services.impl;

import com.example.bookingsystem.dtos.*;
import com.example.bookingsystem.models.Rum;
import com.example.bookingsystem.repo.BokningRepo;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.repo.RumRepo;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.RumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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
    public List<DetaljerRumDto> findEmptyRum(LocalDate date, LocalDate endDate) {

        List<LocalDate> searchDates = date.datesUntil(endDate.plusDays(1)).toList();

        //Filterar bort detaljerade rumDTO:er som har bokade datum som krockar med date och enDate.
        return rumRepo.findAll().stream().map(this::rumToDetaljerRumDTO).toList().stream()
                .filter(r -> Collections.disjoint(r.getBokningar().stream()
                        .flatMap(b-> b.getDate().datesUntil(b.getEndDate().plusDays(1)))
                        .toList(),searchDates)).collect(Collectors.toList());



                /*
                .map(rDTO -> rDTO.getBokningar().stream()
                        .map(b -> b.getDate().datesUntil(b.getEndDate().plusDays(1)))
                        .collect(Collectors.toList())).toList();

                 */
    }

    @Override
    public DetaljerRumDto rumToDetaljerRumDTO(Rum rum) {

        return DetaljerRumDto.builder()
                .id(rum.getId())
                .type(rum.getType())
                .capacity(rum.getCapacity())
                .bokningar(rum.getBokningar().stream().map(bokningService::bokningToBokningDto).toList())
                .build();
    }


}
