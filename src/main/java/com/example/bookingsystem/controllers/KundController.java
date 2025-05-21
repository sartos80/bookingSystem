package com.example.bookingsystem.controllers;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.KundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KundController {

    //private final KundService kundService;
    private final KundRepo kundRepo; //test med repo

    @RequestMapping("/kunder")
    public String getAllKunder(Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        return "kunder.html";
    }

    @PostMapping("/postKunder")
    public String getAllKunder(@RequestParam String namn,@RequestParam String epost,
                               @RequestParam String telefonummer, Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        Kund kund = Kund.builder().name(namn).epost(epost).telefonnummer(telefonummer).build();
        kundRepo.save(kund);
        return "kunder.html";
    }
}
