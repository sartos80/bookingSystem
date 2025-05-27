package com.example.bookingsystem.controllers;
import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.KundService;
import com.example.bookingsystem.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class RumController {

    private final KundService kundService;
    private final BokningService bokningService;
    private final RumService rumService;

    @GetMapping("/addSearchRum")
    public String searchRum(@RequestParam LocalDate date,
                            @RequestParam LocalDate endDate,
                            @RequestParam Long kundId,
                            @RequestParam int capacity ,Model model) {

        model.addAttribute("kund",kundService.getKundById(kundId));

        if(rumService.findEmptyRum(date,endDate,capacity) != null) {

            model.addAttribute("rumFound", rumService.findEmptyRum(date,endDate,capacity));
            model.addAttribute("date", date);
            model.addAttribute("endDate", endDate);
            return "addBokning";

        } else {

            model.addAttribute("capacity", rumService.roomMaxCapacity());
            model.addAttribute("error", "Start datum måste vara innan slut datum");
            return "addRumSearch";
        }


    }

    @GetMapping("/updateSearchRum")
    public String updateSearchRum(@RequestParam LocalDate date,
                                  @RequestParam LocalDate endDate,
                                  @RequestParam Long bokningId,
                                  @RequestParam int capacity ,Model model) {


        DetaljerBokningDto bokning = bokningService.getBokningById(bokningId);
        model.addAttribute("kund",kundService.getKundById(bokning.getKund().getId()));
        model.addAttribute("bokning",bokning );

        if(rumService.findEmptyRum(date,endDate,capacity) != null) {

        model.addAttribute("rumFound", rumService.findEmptyRumIgnoreCurrent(date,endDate,capacity,bokning));
        model.addAttribute("date", date);
        model.addAttribute("endDate", endDate);

        return "updateBokning";

        } else {

            model.addAttribute("capacity", rumService.roomMaxCapacity());
            model.addAttribute("error", "Start datum måste vara innan slut datum");
            return "addRumSearch";
        }
    }
}

