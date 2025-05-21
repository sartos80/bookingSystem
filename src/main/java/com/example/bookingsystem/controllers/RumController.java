package com.example.bookingsystem.controllers;


import com.example.bookingsystem.repo.RumRepo;
import com.example.bookingsystem.services.RumService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RumController {

    private final RumRepo rumRepo;

    RumController(RumRepo rumRepo) {
        this.rumRepo = rumRepo;
    }
}
