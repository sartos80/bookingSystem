package com.example.bookingsystem.controllers;

import com.example.bookingsystem.models.Kund;
import com.example.bookingsystem.repo.KundRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@       Controller
@RequiredArgsConstructor
public class KundController {
    private final KundRepo kundRepo;
    @RequestMapping("kunder")
    public List<Kund>getAllKunder(){
        return kundRepo.findAll();

    }
}
