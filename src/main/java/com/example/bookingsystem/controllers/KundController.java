package com.example.bookingsystem.controllers;

import com.example.bookingsystem.dtos.DetaljerKundDto;
import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.KundRepo;
import com.example.bookingsystem.services.KundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/kunder")
public class KundController {

    private final KundService kundService;

    @RequestMapping("/all")
    public String getAllKunder(Model model) {
        List<DetaljerKundDto> kunder = kundService.getAllKunder();
        model.addAttribute("kunder", kunder);
        return "kunder ";
    }
}
