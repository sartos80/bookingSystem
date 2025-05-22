package com.example.bookingsystem.controllers;


import com.example.bookingsystem.repo.RumRepo;
import com.example.bookingsystem.services.KundService;
import com.example.bookingsystem.services.RumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class RumController {
    private final RumService rumService;
