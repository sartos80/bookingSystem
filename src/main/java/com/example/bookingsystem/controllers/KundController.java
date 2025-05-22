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
public class KundController {

    //private final KundService kundService;
    private final KundRepo kundRepo; //test med repo

    @RequestMapping("/addKund")
    public String getKundForm(Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        return "kunderForm.html";
    }

    @RequestMapping("/kunderAll")
    public String getAllKunder(Model model) {
        List<Kund> kunder = kundRepo.findAll();
        model.addAttribute("allKunder", kunder);
        model.addAttribute("name","Kunder" );
        return "kunderAll.html";
    }

    @PostMapping("/postKund")
    public String getAllKunder(@RequestParam String namn,@RequestParam String epost,
                               @RequestParam String telefonummer, Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        //TODO Ändra till DTO
        Kund kund = Kund.builder().name(namn).epost(epost).telefonnummer(telefonummer).build();
        kundRepo.save(kund);
        return "redirect:/kunderAll";
    }

    @RequestMapping("/deleteKund/{id}")
    public String deleteKund(@PathVariable Long id, Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        //TODO Ändra till DTO
        kundRepo.deleteById(id);
        return "redirect:/kunderAll";
    }

    @RequestMapping("/updateKund/{id}")
    public String updateKund(@PathVariable Long id, Model model) {
        //List<DetaljerKundDto> kunder = kundService.getAllKunder();
        //model.addAttribute("kunder", kunder);
        //TODO Ändra till DTO
        Kund kund = kundRepo.findById(id).get();
        model.addAttribute("kund", kund);
        model.addAttribute("name",kund.getName());
        return "updateBokning.html";
    }
}
