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
public class KundController {

    private final KundService kundService;
    private final BokningService bokningService;
    private final RumService rumService;

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

    @GetMapping("/addBokning/{id}")
    public String addBokning(@PathVariable Long id, Model model) {
        DetaljerKundDto kund = kundService.getKundById(id);
        model.addAttribute("kund", kund);
        model.addAttribute("name", kund.getName());
        return "rumSearch";
    }



    @GetMapping("/deleteBokning/{id}")
    public String deleteBokning(@PathVariable Long id) {
        bokningService.deleteBokning(id);
        return "redirect:/kunder/kunderAll";
    }

    @GetMapping("/updateBokning/{id}")
    public String updateBokning(@PathVariable Long id, Model model) {
        DetaljerBokningDto bokning = bokningService.getBokningById(id);
        model.addAttribute("name", "Bokning");
        model.addAttribute("bokning", bokning);
        return "updateBokning";
    }

    @GetMapping("/postBokning/{id}")
    public String postBokning(@ModelAttribute DetaljerBokningDto bokningDto) {
        bokningService.addBokning(bokningDto);//
        return "redirect:/kunder/kunderAll";
    }

    @GetMapping("/searchRum")
    public String searchRum(@RequestParam LocalDate date,
                            @RequestParam LocalDate endDate,
                            @RequestParam Long kundId, Model model) {
        DetaljerKundDto kund = kundService.getKundById(kundId);
          model.addAttribute("rumFound", rumService.findEmptyRum(date,endDate));
          model.addAttribute("kund",kund );
        model.addAttribute("name", kund.getName());
        return "addBokning";
    }
/*
    @PostMapping("/updateKund")
    public String updateKund(@ModelAttribute DetaljerKundDto kundDto) {
        kundService.addKund(kundDto); // addKund hanterar både ny & uppdaterad pga ID
        return "redirect:/kunder/kunderAll";
    }

 */
}