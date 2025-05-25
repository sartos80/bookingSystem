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
                            @RequestParam Long kundId, Model model) {
        model.addAttribute("rumFound", rumService.findEmptyRum(date,endDate));
        model.addAttribute("kund",kundService.getKundById(kundId));
        //model.addAttribute("name", kund.getName());
        model.addAttribute("date", date);
        model.addAttribute("endDate", endDate);

        return "addBokning";
    }

    @GetMapping("/updateSearchRum")
    public String updateSearchRum(@RequestParam LocalDate date,
                                  @RequestParam LocalDate endDate,
                                  @RequestParam Long bokningId, Model model) {
        DetaljerBokningDto bokning = bokningService.getBokningById(bokningId);
        model.addAttribute("rumFound", rumService.findEmptyRumIgnoreCurrent(date,endDate,bokning));
        model.addAttribute("kund",kundService.getKundById(bokning.getKund().getId()));
        model.addAttribute("bokning",bokning );
        model.addAttribute("date", date);
        model.addAttribute("endDate", endDate);

        return "updateBokning";
    }
}

