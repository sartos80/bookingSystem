package com.example.bookingsystem.controllers;

import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.KundService;
import com.example.bookingsystem.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class BokningController {


    private final KundService kundService;
    private final BokningService bokningService;
    private final RumService rumService;


    @GetMapping("/addBokning/{id}")
    public String addBokning(@PathVariable Long id, Model model) {
        DetaljerKundDto kund = kundService.getKundById(id);
        model.addAttribute("kund", kund);
        return "addRumSearch";
    }

    @GetMapping("/updateBokning/{id}")
    public String updateBokning(@PathVariable Long id, Model model) {
        DetaljerBokningDto bokning = bokningService.getBokningById(id);
        DetaljerKundDto kund = kundService.getKundById(bokning.getKund().getId());
        model.addAttribute("kund", kund);
        model.addAttribute("bokning", bokning);
        return "updateRumSearch";
    }

    @GetMapping("/deleteBokning/{id}")
    public String deleteBokning(@PathVariable Long id) {
        bokningService.deleteBokning(id);
        return "redirect:/kunder/kunderAll";
    }

    @PostMapping("/postBokning")
    public String postBokning(@ModelAttribute DetaljerBokningDto bokningDto) {
        bokningService.addBokning(bokningDto);//
        return "redirect:/kunder/kunderAll";
    }
}
