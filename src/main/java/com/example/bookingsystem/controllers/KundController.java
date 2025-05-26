package com.example.bookingsystem.controllers;

import com.example.bookingsystem.dtos.DetaljerBokningDto;
import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.services.BokningService;
import com.example.bookingsystem.services.KundService;
import com.example.bookingsystem.services.RumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class KundController {

    private final KundService kundService;
    private final BokningService bokningService;
    private final RumService rumService;

    @GetMapping("/addKund")
    public String getKundForm(Model model) {
        model.addAttribute("kund", new DetaljerKundDto());
        return "addKund";
    }

    @PostMapping("/postKund")
    public String postKund(@Valid @ModelAttribute("kund") DetaljerKundDto kundDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addKund";
        }
        kundService.addKund(kundDto);
        return "redirect:/kunder/kunderAll";
    }

    @GetMapping("/kunderAll")
    public String getAllKunder(Model model) {
        model.addAttribute("allKunder", kundService.getAllKunder());
        model.addAttribute("name", "Kunder");
        return "kunderAll";
    }

    @GetMapping("/deleteKund/{id}")
    public String deleteKund(@PathVariable Long id) {
        kundService.deleteKund(id);
        return "redirect:/kunder/kunderAll";
    }

    @GetMapping("/updateKund/{id}")
    public String updateKund(@PathVariable Long id, Model model) {
        DetaljerKundDto kund = kundService.getKundById(id);
        model.addAttribute("kund", kund);
        model.addAttribute("name", kund.getName());
        return "updateKund";
    }
}