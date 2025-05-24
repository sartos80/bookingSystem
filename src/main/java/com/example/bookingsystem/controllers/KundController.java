package com.example.bookingsystem.controllers;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.KundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class KundController {

    private final KundService kundService;

    @GetMapping("/addKund")
    public String getKundForm(Model model) {
        model.addAttribute("kund", new DetaljerKundDto());
        return "kunderForm";
    }

    @PostMapping("/postKund")
    public String postKund(@ModelAttribute DetaljerKundDto kundDto) {
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

    @PostMapping("/updateKund")
    public String updateKund(@ModelAttribute DetaljerKundDto kundDto) {
        kundService.addKund(kundDto); // addKund hanterar både ny & uppdaterad pga ID
        return "redirect:/kunder/kunderAll";
    }
}